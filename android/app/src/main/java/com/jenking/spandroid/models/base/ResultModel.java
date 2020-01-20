package com.jenking.spandroid.models.base;

import java.util.List;

/**
 * Created by zhouzhenjian on 2018/4/24.
 */

public class ResultModel<T> {

    private String code;
    private String message;
    private T data;
    private List<T> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
