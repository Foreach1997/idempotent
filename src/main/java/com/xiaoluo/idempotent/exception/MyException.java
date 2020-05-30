package com.xiaoluo.idempotent.exception;

import com.xiaoluo.idempotent.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Project:               idempotent
 *
 * @Author: ljw
 * Company:               Big Player Group
 * Created Date:          2020/5/29
 * Description:   {类描述}
 * Copyright @ 2017-2020 BIGPLAYER.GROUP – Confidential and Proprietary
 * <p>
 * History:
 * ------------------------------------------------------------------------------
 * Date            |time        |Author    |Change Description
 */
@ControllerAdvice
@Slf4j
public class MyException {
    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =BizException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, BizException e){
        log.info(e.getMsg());
        return Result.error(e.getMsg());
    }

}
