package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface ISearchService {
    /*
    * 通过关键字检索商品
    * */
    List<Goods> searchGoods(String keyWorld);

    /*
    * 将添加的商品同步到索引库
    * */
    int insert(Goods goods);

}
