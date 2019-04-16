package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsServive;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsServive {

    @Autowired
    private GoodsMapper goodsMapper;

    @Reference
    private ISearchService searchService;

    @Override
    public List<Goods> queryAll() {
        return goodsMapper.selectList(null);
    }

    @Override
    public int intsert(Goods goods) {

       int result =  goodsMapper.insert(goods);

        /*
        * 通过dubbo调搜索服务，然后在搜索服务里面去完成具体同步的操作
        * */
        searchService.insert(goods);

        return result;
    }

    @Override
    public Goods selectById(int id) {
        return goodsMapper.selectById(id);
    }
}
