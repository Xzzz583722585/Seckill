package com.xqz.seckill.common.result;

import com.xqz.seckill.common.enums.ResultStatus;

import java.io.Serializable;

public class ResultMsg<T> extends AbstractMsg implements Serializable {

    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected ResultMsg(ResultStatus status) {
        super(status);
    }

    public static <T> ResultMsg<T> build(ResultStatus status) {
        return new ResultMsg(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}