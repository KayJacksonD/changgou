package com.changgou.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler
 * @Description 统一异常的处理
 * @Author 传智播客
 * @Date 12:38 2019/10/16
 * @Version 2.1
 **/
@ControllerAdvice   // 对方法进行增强，@RequestMapping的请求方法增强
public class BaseExceptionHandler {

    /**
     * @author 栗子
     * @Description 处理异常的方法
     * @Date 12:42 2019/10/16
     * @param e
     * @return entity.Result
     **/
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result errorMessage(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
