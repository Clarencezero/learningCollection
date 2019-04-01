package com.zhangwenfeng.learningcollection.plmcodetemplate;

import lombok.Data;

import java.io.Serializable;

/**
 * 为了统一格式
 * 为了应用AOP
 * 为了包装异常信息
 */
@Data
public class ResultBean<T> implements Serializable {

    public static final int NO_LOGIN = -1;
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL ;
    }
}
