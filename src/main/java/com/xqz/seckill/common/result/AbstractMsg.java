package com.xqz.seckill.common.result;

import com.xqz.seckill.common.enums.ResultStatus;

public abstract class AbstractMsg {
    private ResultStatus status;
    private Integer code;
    private String message;

    protected AbstractMsg(ResultStatus status) {
        this.status = status;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static boolean isSuccess(AbstractMsg result) {
        return result != null && result.status == ResultStatus.SUCCESS;
    }

    public ResultStatus getStatus() {
        return this.status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
