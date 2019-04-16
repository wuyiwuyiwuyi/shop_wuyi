package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService {


    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Goods> searchGoods(String keyWorld) {

        System.out.println("搜索服务获取到关键字："+keyWorld);

        //首先创建查询对象
        SolrQuery solrQuery = new SolrQuery();

        if (keyWorld==null){
                //sou搜索全部
            solrQuery.setQuery("*:*");
     }else{
                //按关键字检索
            solrQuery.setQuery("gname:"+keyWorld+" || ginfo:"+keyWorld);
        }

        //开启高亮
        solrQuery.setHighlight(true);
        //设置前缀和后缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        List<Goods> list = new ArrayList<>();
        try {
            QueryResponse result = solrClient.query(solrQuery);

            //获得高亮结果
            Map<String, Map<String, List<String>>> highlighting = result.getHighlighting();
            //此处需注意map里面的参数含义


            SolrDocumentList results = result.getResults();
            //将搜索结果集遍历
           for (SolrDocument document : results) {
                    Goods goods = new Goods();
                    goods.setId(Integer.parseInt(document.get("id")+""));
                    goods.setGname(document.get("gname")+"");
                    goods.setGprice(BigDecimal.valueOf(Double.parseDouble(document.get("gprice")+"")));
                    goods.setGimage(document.get("gimage")+"");
                    goods.setGsave(Integer.parseInt(document.get("gsave")+""));

                    //在循环的结果里面判断当前商品是否有高亮
               if(highlighting.containsKey(goods.getId()+"")){

                   //有高亮的内容
                   Map<String, List<String>> stringListMap = highlighting.get(goods.getId() + "");
                   //获取高亮的内容
                   String gname = stringListMap.get("gname").get(0);
                   //将高亮的内容替换到对象中
                   goods.setGname(gname);

               }


                    list.add(goods);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    /*
    * 完成将商品同步到索引库
    * */

    @Override
    public int insert(Goods goods) {

        //创建document对象
        SolrInputDocument document = new SolrInputDocument();

        document.addField("id", goods.getId());
        document.addField("gname", goods.getGname());
        document.addField("gimage", goods.getGimage());
        document.addField("gprice", goods.getGprice());
        document.addField("gsave", goods.getGsave());
        document.addField("ginfo", goods.getGinfo());

        try {
            solrClient.add(document);
            solrClient.commit();
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
