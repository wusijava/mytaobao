package com.taobao.taobao.test;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import com.taobao.taobao.utils.WeatherFastJsonUtils;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @ Description   :  测试fastjson
 * @ Author        :  wusi
 * @ CreateDate    :  2019/11/29$ 11:47$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class testFastJson {

    @Test
    public void getFastJson(){
      String weather = WeatherFastJsonUtils.getWeather();
        System.out.println(weather);
    }


}
