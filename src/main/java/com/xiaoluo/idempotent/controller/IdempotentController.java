/*
package com.xiaoluo.idempotent.controller;

import com.xiaoluo.idempotent.annotation.Idempotent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * Project:               idempotent
 *
 * @Author: ljw
 * Created Date:          2020/5/29
 * Description:   {类描述}
 * <p>
 * History:
 * ------------------------------------------------------------------------------
 * Date            |time        |Author    |Change Description
 *//*

@RestController
@RequestMapping("/test")
public class IdempotentController {


    @GetMapping("/lockTest")
    @Idempotent(key = "#id",time = 15000L)
    public String lockTest(String id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        return "success";
    }








}
*/
