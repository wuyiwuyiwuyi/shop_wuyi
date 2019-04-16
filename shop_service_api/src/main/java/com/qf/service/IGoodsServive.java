package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsServive {

    /*
    * 查询所有商品
    * */
    List<Goods> queryAll();

    /*
    * 添加商品
    * */
    int intsert(Goods goods);

    Goods selectById(int id);


}
