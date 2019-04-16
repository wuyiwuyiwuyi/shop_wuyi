package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsServive;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Reference
    private IGoodsServive goodsServive;

    @Autowired
    private Configuration configuration;

    @RequestMapping("/createHtml")
    public String createHtml(int id, HttpServletRequest request){

       Goods goods = goodsServive.selectById(id);


        try {
            //获得商品的模板对象
            Template template = configuration.getTemplate("goodsItem.ftl");

            //创建一个集合用来存商品信息
            Map<String,Object> map = new HashMap<>();
            map.put("goods",goods);
            map.put("context",request);

            //生成静态页，怎样生成？
            //怎样去获取生成页面的路径classpath
            //怎样将所展示的商品跟展示的页面的关联起来？（利用商品的ID）

          String path =  this.getClass().getResource("/static/item/").getPath()+goods.getId()+".html";



        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
