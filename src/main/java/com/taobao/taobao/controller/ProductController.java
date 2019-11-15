package com.taobao.taobao.controller;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import com.taobao.taobao.utils.SendMail;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Scheduled(cron = "0 0 16-23 * * ?")
    public void getlist() throws Exception {
        List<Product> list = productMapper.selectAll();
        for (Product pro : list) {
            String url = pro.getUrl();
           // System.out.println(url);
            String html = null;
            try {
                html = Jsoup.connect(url).execute().body();
               // System.out.println(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //打印页面内容
            //String json= JSON.toJSONString(html);
            String value = GetJsonValue(html, "online");
           //System.out.println(value);
            String newvalue=value.trim();
            if("false".equalsIgnoreCase(newvalue)){
                //System.out.println("测试定时任务！");
                SendMail.sendQQMail("吴老板:"+url+"的商品下架了，请尽快处理！");
            }
            //System.out.println(value);
        }

    }
    public static String GetJsonValue(String jsonStr, String key)
    {

        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+18, index+24);
        return result;
    }
    public static String Getname(String jsonStr, String key)
    {

        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+18, index+24);
        return result;
    }
}