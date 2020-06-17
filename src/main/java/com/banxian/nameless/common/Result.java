package com.banxian.nameless.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result success(String mess, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
