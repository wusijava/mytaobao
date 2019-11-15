package com.taobao.taobao.entity;

import lombok.Data;

@Data
public class Product implements Identifiable<Long> {
    private Long id;
    private String url;
    private String state;
    public Product() {
    }


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
