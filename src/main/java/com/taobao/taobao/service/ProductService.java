package com.taobao.taobao.service;

import com.taobao.taobao.base.service.BaseService;
import com.taobao.taobao.entity.Product;

/**
 * @author Java
 * @date 2019-11-13 22:45:30
 **/

public interface ProductService extends BaseService<Product,Long> {
    Product getStateByUrl(String Url);
    void updateByUrl(String url,String type);
}
