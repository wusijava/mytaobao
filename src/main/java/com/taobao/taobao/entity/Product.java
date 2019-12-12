package com.taobao.taobao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product implements Identifiable<Long> {
    private Long id;
    private String url;
    private String state;
    private String type;
    private Date updatetime;
    public Product() {
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

        }
