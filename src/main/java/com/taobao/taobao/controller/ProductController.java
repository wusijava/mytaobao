package com.taobao.taobao.controller;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/")
    @ResponseBody
    public void getlist() {
        List<Product> list = productMapper.selectAll();
        for (Product pro : list) {
            String url = pro.getUrl();
            System.out.println(url);
            String html = null;
            try {
                html = Jsoup.connect(url).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //打印页面内容
            //String json= JSON.toJSONString(html);
            String value = GetJsonValue(html, "online");
            if("false".equalsIgnoreCase(value)){

            }
            System.out.println(value);
        }

    }
    public static String GetJsonValue(String jsonStr, String key)
    {

        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+18, index+24);
        return result;
    }
}