package com.taobao.taobao.controller;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import com.taobao.taobao.utils.SendMail;
import com.taobao.taobao.utils.WeatherUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value = "/run" )
    @ResponseBody
    @Scheduled(cron = "0 0 12 * * ?")
    public void getlist() throws Exception {
        //加入每日任务提醒 确保程序已启动
        //加入天气情况
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        List<Product> list = productMapper.selectAll();
        int i=0;
        for (Product pro : list) {
            i++;
           Thread.sleep(100000);
            String url = pro.getUrl();
            String html = null;
            try {
                //增加延迟时间  线上
                html = Jsoup.connect(url).timeout(100000).execute().body();
                //本地
               //html = Jsoup.connect(url).execute().body();
            } catch (IOException e) {
                log.error("执行失败",e.getMessage());
                e.printStackTrace();
            }

            //获取线上商品状态
            String value = GetJsonValue(html, "online");
            //获取数据库商品状态
            String sqlvalue=productMapper.getStateByUrl(url).getState();
            //获取商品名称
            Product product = productMapper.getStateByUrl(url);
            String type=product.getType();
            String newvalue=value.trim();
            if ("false".equalsIgnoreCase(newvalue)&&"online".equalsIgnoreCase(sqlvalue)){
                //更新数据库商品状态
                productMapper.updateByUrl(url,"offline");
                //发送邮件提醒
                SendMail.sendQQMail(df.format(new Date())+"----吴老板:"+type+"的商品下架了，请尽快处理！");
            }
            if ("true,".equalsIgnoreCase(newvalue)&&"offline".equalsIgnoreCase(sqlvalue)){
                //更新数据库商品状态
                productMapper.updateByUrl(url,"online");
                //发送邮件提醒
                SendMail.sendQQMail(df.format(new Date())+"----吴老板:"+type+"的商品上架了，请尽快在店内上架！");
            }

        }
        SendMail.sendQQMail(df.format(new Date())+"的监控任务已启动!"+"\n"+"共计扫描商品"+i+"次！"+ WeatherUtils.getWeather());
    }
    public static String GetJsonValue(String jsonStr, String key)
    {   //log.error("入参---->:{}"+jsonStr);
        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+18, index+24);
        return result;
    }

}