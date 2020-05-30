package com.xiaoluo.idempotent.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{

    private String msg;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
