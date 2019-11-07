package com.eknows.model.bean.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eknows
 * @version 1.0
 * @since 2018/12/26 14:35
 */
public class JsonResult implements Serializable {

    public static final int ACCESS_TOKEN_INVALID = 50;

    private static final long serialVersionUID = 7911919307011379110L;
    private Integer code;
    private Boolean success;
    private String msg;
    private Object data;

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public JsonResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public void put(Object key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        ((Map) data).put(key, value);
    }

    public void putAll(Map<Object, Object> map) {
        if (data == null) {
            data = new HashMap<>();
        }
        ((Map) data).putAll(map);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"success\":" + success + ",\"msg\":" + msg + ",\"code\":" + code
            + ",\"data\":" + data + "}";
    }


}