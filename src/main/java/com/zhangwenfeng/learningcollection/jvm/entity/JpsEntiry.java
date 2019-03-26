package com.zhangwenfeng.learningcollection.jvm.entity;

import lombok.Data;

import java.util.List;

@Data
public class JpsEntiry {
    /**
     * 全称
     */
    private String className;
    /**
     * 简称
     */
    private String abbreviation;
    /**
     * 参数
     */
    private List<String> parameters;

    public JpsEntiry(String className, String abbreviation, List<String> parameters) {
        this.className = className;
        this.abbreviation = abbreviation;
        this.parameters = parameters;
    }
}
