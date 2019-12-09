package com.taobao.taobao.service.impl;

import com.taobao.taobao.base.dao.mybatis.BaseMapper;
import com.taobao.taobao.base.service.impl.BaseMybatisServiceImpl;
import com.taobao.taobao.entity.Product;
import com.taobao.taobao.mapper.ProductMapper;
import com.taobao.taobao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;

public class ProductServiceImpl  extends BaseMybatisServiceImpl<Product,Long> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    protected BaseMapper<Product, Long> getBaseMapper() {
        return null;
    }

    @Override
    public Product getStateByUrl(String Url) {
        return productMapper.getStateByUrl(Url);
    }

    @Override
    public void updateByUrl(String url, String state) {
        productMapper.updateByUrl(url,state);
    }


    @Override
    public <Q extends Product> Page<Product> queryPage(Q query, SpringDataWebProperties.Pageable pageable) {
        return null;
    }
}
