package com.taobao.taobao.test;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import com.taobao.taobao.utils.SendMail;
import com.taobao.taobao.utils.WeatherUtils;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ Description   :  获得商品名称并同步到线上数据库
 * @ Author        :  wusi
 * @ CreateDate    :  2019/11/29$ 11:47$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class testGetName {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void getName(){
        List<Product> list = productMapper.selectAll();
        for (Product pro : list) {
            String url = pro.getUrl();
            // System.out.println(url);
            String html = null;
            try {
                //增加延迟时间
                html = Jsoup.connect(url).execute().body();
                System.out.println(html);
               String type= GetName(html,"data-title");
                productMapper.updateByUrl(url, type);
            } catch (IOException e) {
                e.printStackTrace();
            }





        }
    }
    public static String GetName(String jsonStr, String key)
    {

        int index=jsonStr.indexOf(key);
        String result=jsonStr.substring(index+12, index+50);
        return result;
    }

}
