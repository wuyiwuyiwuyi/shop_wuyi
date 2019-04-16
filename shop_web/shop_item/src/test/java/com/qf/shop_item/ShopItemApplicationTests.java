package com.qf.shop_item;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopItemApplicationTests {


	@Autowired
	private Configuration configuration;

	@Test
	public void contextLoads() throws Exception {

		//准备一个静态页面输出的位子
		String path = "C:\\Users";
		Writer writer = new FileWriter(path);

		//通过配置对象获取模板对象
		Template template = configuration.getTemplate("hello.ftl");

		//准备数据部分
		Map<String,Object> data = new HashMap<>();

		data.put("key","wuyi");

		//将模板和数据进行静态化合并
		template.process(data, new FileWriter(path));

		writer.close();

	}

}
