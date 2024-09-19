package com.xian.global;

import com.xian.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Result<Object> handleException(RuntimeException e) {
        // 返回一个包含错误信息的 HTTP 响应
        e.printStackTrace();
        return  Result.error(e.getMessage());
    }

}

