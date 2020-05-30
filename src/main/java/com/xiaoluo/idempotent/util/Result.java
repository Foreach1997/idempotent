package com.xiaoluo.idempotent.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * Project:               ${PROJECT_NAME}
 * Author:                crh
 * Company:               Big Player Group
 * Created Date:          ${DATE}
 * Description:   {类描述}
 * Copyright @ 2017-${YEAR} BIGPLAYER.GROUP – Confidential and Proprietary
 * <p>
 * History:
 * ------------------------------------------------------------------------------
 * Date            |time        |Author    |Change Description
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Integer SUCCESS = 0;
	private static final Integer FAIL = -1;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	public boolean isSuccess() {
		return SUCCESS.equals(this.getCode());
	}

	public static <T> Result<T> success() {
		return restResult(null, SUCCESS, "ok");
	}

	public static <T> Result<T> success(T data) {
		return restResult(data, SUCCESS, "ok");
	}

	public static <T> Result<T> success(T data, String msg) {
		return restResult(data, SUCCESS, msg);
	}

	public static <T> Result<T> error() {
		return restResult(null, FAIL, null);
	}

	public static <T> Result<T> error(String msg) {
		return restResult(null, FAIL, msg);
	}

	public static <T> Result<T> error(T data) {
		return restResult(data, FAIL, null);
	}

	public static <T> Result<T> error(T data, String msg) {
		return restResult(data, FAIL, msg);
	}

	private static <T> Result<T> restResult(T data, int code, String msg) {
		Result<T> apiResult = new Result<>();
		apiResult.setCode(code);
		apiResult.setMsg(msg);
		apiResult.setData(data);
		return apiResult;
	}
}