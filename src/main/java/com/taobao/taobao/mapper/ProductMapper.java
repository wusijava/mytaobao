package com.taobao.taobao.mapper;

import com.taobao.taobao.base.dao.mybatis.BaseMapper;
import com.taobao.taobao.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Java
 * @date 2019-11-13 22:45:30
 **/
@Mapper
public interface ProductMapper extends BaseMapper<Product,Long> {

void updateByUrl(String url,String state);
Product getStateByUrl(String Url);

}
