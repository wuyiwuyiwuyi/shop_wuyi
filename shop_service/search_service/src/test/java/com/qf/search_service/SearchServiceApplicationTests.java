package com.qf.search_service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceApplicationTests {

	@Autowired
	private SolrClient solrClient;

	/**
	 * 添加索引
	 *
	 * id相同就是修改 id不同就是增加
	 *
	 */
	@Test
	public void add(){

		//查询数据库 - Goods

		//创建document对象
		SolrInputDocument solrDocument = new SolrInputDocument();
		solrDocument.addField("id", 4);
		solrDocument.addField("gname", "小天鹅洗衣机");
		solrDocument.addField("gimage", "http://www.baidu.com");
		solrDocument.addField("ginfo", "洗衣机中的战斗机");
		solrDocument.addField("gprice", 99.99);
		solrDocument.addField("gsave", 10000);

		try {
			solrClient.add(solrDocument);
			solrClient.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
