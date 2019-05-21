package com.xqz.seckill.common.result;

import com.xqz.seckill.common.enums.ResultStatus;

public abstract class AbstractMsg {
    private ResultStatus status;
    private int code;
    private String message;

    protected AbstractMsg(ResultStatus status, String message) {
        this.status = status;
        this.code = status.getCode();
        this.message = message;
    }

    protected AbstractMsg(ResultStatus status) {
        this.status = status;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static boolean isSuccess(AbstractMsg result) {
        return result != null && result.status == ResultStatus.SUCCESS && result.getCode() == ResultStatus.SUCCESS.getCode();
    }

    public AbstractMsg withError(ResultStatus status) {
        this.status = status;
        return this;
    }

    public AbstractMsg withError(String message) {
        this.status = ResultStatus.SYSTEM_ERROR;
        this.message = message;
        return this;
    }

    public AbstractMsg withError(int code, String message) {
        this.status = ResultStatus.SYSTEM_ERROR;
        this.code = code;
        this.message = message;
        return this;
    }

    public AbstractMsg success() {
        this.status = ResultStatus.SUCCESS;
        return this;
    }
    public ResultStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message == null ? this.status.getMessage() : this.message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
