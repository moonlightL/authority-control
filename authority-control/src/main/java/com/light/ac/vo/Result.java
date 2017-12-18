package com.light.ac.vo;

public class Result {

    private static final String SUCCESS = "操作成功";

    private static final String FAILURE = "操作失败";

    private int code;

    private String msg;

    private Object obj;

    public Result(int code, String msg) {
        this(code,msg,null);
    }

    public Result(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static Result succeed() {
        return new Result(200,SUCCESS);
    }

    public static Result succeed(Object obj) {
        return new Result(200,SUCCESS,obj);
    }

    public static Result succeed(String msg, Object obj) {
        return new Result(200,msg,obj);
    }

    public static Result fail() {
        return new Result(500,FAILURE);
    }

    public static Result fail(int code, String msg) {
        return new Result(code,msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
