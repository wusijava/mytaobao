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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/run")
    @ResponseBody
    @Scheduled(cron = "0 0 22 * * ?")
    public void getlist() throws Exception {
        List<Product> list = productMapper.selectAll();
        for (Product pro : list) {
            Thread.sleep(100000);
            String url = pro.getUrl();
           // System.out.println(url);
            String html = null;
            try {
                //增加延迟时间
                html = Jsoup.connect(url).timeout(200000).execute().body();
               // System.out.println(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //打印页面内容
            //String json= JSON.toJSONString(html);
            //获取线上商品状态
            String value = GetJsonValue(html, "online");
            //获取数据库商品状态
            String sqlvalue=productMapper.getStateByUrl(url).getState();
            String newvalue=value.trim();
            //加入每日任务提醒 确保程序已启动
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
            SendMail.sendQQMail(df.format(new Date())+"的监控任务已启动!");
            if ("false".equalsIgnoreCase(newvalue)&&"online".equalsIgnoreCase(sqlvalue)){
                //更新数据库商品状态
                productMapper.updateByUrl(url,"offline");
                //发送邮件提醒
                SendMail.sendQQMail("吴老板:"+url+"的商品下架了，请尽快处理！");
            }
            if ("true,".equalsIgnoreCase(newvalue)&&"offline".equalsIgnoreCase(sqlvalue)){
                //更新数据库商品状态
                productMapper.updateByUrl(url,"online");
                //发送邮件提醒
                SendMail.sendQQMail("吴老板:"+url+"的商品上架了，请尽快在店内上架！");
            }

        }

    }
    public static String GetJsonValue(String jsonStr, String key)
    {

        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+18, index+24);
        return result;
    }

}