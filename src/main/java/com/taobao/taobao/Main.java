package com.taobao.taobao;

import com.taobao.taobao.entity.Product;
import com.taobao.taobao.generator.CodeGenerator;

/**
 * @author lvlu
 * @date 2019-08-29 15:13
 **/
public class Main {

    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator generator = new CodeGenerator();
        generator.generateMybatisXml(basePack, Product.class);
      generator.generateService(basePack,Product.class);
       generator.generateDao(basePack,Product.class);
    }
}
