package com.xiaoluo.idempotent.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    String key() default "";

    long time() default 0L;

}
