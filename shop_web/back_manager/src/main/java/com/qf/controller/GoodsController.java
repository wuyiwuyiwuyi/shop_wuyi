package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsServive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsServive goodsServive;


    @Value("${server.ip}")
    private String serverIp;


    @RequestMapping("/list")
    public String queryAll(Model model){

      List<Goods> goodsList = goodsServive.queryAll();
        System.out.println("哈哈哈");
      model.addAttribute("goods",goodsList);
      model.addAttribute("serverip",serverIp);

        return "goodsList";
    }

    @RequestMapping("/insert")
    public String insert(Goods goods){

        goodsServive.intsert(goods);

        return "redirect:/goods/list";
    }
}
