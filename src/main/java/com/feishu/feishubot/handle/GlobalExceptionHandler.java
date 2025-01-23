package com.feishu.feishubot.handle;

import com.feishu.feishubot.domain.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 14:57
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有未捕获的异常
     *
     * @param e 异常对象
     * @return 统一错误响应
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("全局异常捕获: ", e);
        return Result.fail("系统异常，请稍后重试");
    }

//    /**
//     * 处理自定义业务异常
//     *
//     * @param e 业务异常对象
//     * @return 统一错误响应
//     */
//    @ExceptionHandler(BusinessException.class)
//    public Result<?> handleBusinessException(BusinessException e) {
//        log.error("业务异常捕获: ", e);
//        return Result.fail(e.getMessage());
//    }

    /**
     * 处理参数校验异常
     *
     * @param e 参数校验异常对象
     * @return 统一错误响应
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数校验异常捕获: ", e);
        return Result.fail("参数错误: " + e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 参数校验异常对象
     * @return 统一错误响应
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("参数校验异常捕获: ", e);
        return Result.fail("参数错误: " + e.getMessage());
    }
}
