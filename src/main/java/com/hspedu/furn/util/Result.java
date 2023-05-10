package com.hspedu.furn.util;

/**
 * @author Zexi He.
 * @date 2023/5/9 18:56
 * @description:
 * 1. Result<T> 是一个自定义泛型类
 * 2. Result<T> 对象就是后端返回给前端的数据，是以 json 格式返回
 */
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }

    //编写方法返回成功消息
    public static Result success() {
        Result<Object> result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    //编写方法返回携带数据的成功消息
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    //编写方法返回失败消息
    public static Result<Object> error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //编写方法返回携带数据的失败消息
    public static <T> Result<T> success(String code, String msg, T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg(msg);
        return result;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
