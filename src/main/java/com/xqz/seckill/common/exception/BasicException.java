package com.xqz.seckill.common.exception;

import com.xqz.seckill.common.enums.ResultStatus;

public class BasicException extends Exception{
    private ResultStatus status;

    public BasicException(ResultStatus status) {
        super();
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
