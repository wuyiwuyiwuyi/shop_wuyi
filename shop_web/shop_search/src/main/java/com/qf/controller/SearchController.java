package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("/searchByKeyWorld")

    public String searchBykeyWorld(String keyworld, Model model){

        System.out.println("搜索工程获取到关键字keyworld为："+keyworld);

        List<Goods> goods = searchService.searchGoods(keyworld);
        System.out.println("获取检索结果："+goods);

        model.addAttribute("goods",goods);

        return "searchList";
    }

}
