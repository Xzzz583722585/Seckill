package com.xqz.seckill.common.result;

import com.xqz.seckill.common.enums.ResultStatus;

public abstract class AbstractMsg {
    private ResultStatus status;

    protected AbstractMsg(ResultStatus status) {
        this.status = status;
    }

    public static boolean isSuccess(AbstractMsg result) {
        return result != null && result.status == ResultStatus.SUCCESS;
    }

    public ResultStatus getStatus() {
        return this.status;
    }
}
