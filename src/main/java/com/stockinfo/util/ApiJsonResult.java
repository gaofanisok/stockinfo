package com.stockinfo.util;



import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Auther: gaofan
 * @Date: 2019/12/12 0012 15 01
 * @Description: 返回前段数据
 */
public class ApiJsonResult<T> implements Serializable {
    private String msg = "";
    private Integer errorCode = 0;
    private T data = null;

    public ApiJsonResult() {
    }

    public ApiJsonResult(String msg) {
        this.msg = msg;
    }

    public ApiJsonResult(String msg, Integer errorCode) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public ApiJsonResult(String msg, Integer errorCode, T data) {
        this.msg = msg;
        this.errorCode = errorCode;
        this.data = data;
    }


    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
