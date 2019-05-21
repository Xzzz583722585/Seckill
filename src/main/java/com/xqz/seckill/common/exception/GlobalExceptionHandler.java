package com.xqz.seckill.common.exception;

import com.xqz.seckill.common.result.ResultMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultMsg<?> validationErrorHandler(Exception ex) {
//        // 同样是获取BindingResult对象，然后获取其中的错误信息
//        // 如果前面开启了fail_fast，事实上这里只会有一个信息
//        //如果没有，则可能又多个
//        List<String> errorInformation = ex.getBindingResult().getAllErrors()
//                .stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList());
//        return new ResultInfo<>(400, errorInformation.toString(), null);
        return null;
    }
}
