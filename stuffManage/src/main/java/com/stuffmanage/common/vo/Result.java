package com.stuffmanage.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{
    private T data;
    private String msg;
    private String code;

    public Result(T data){
        this.data = data;
    }
    public static Result success(){
        Result Result = new Result<>();
        Result.setCode("200");
        Result.setMsg("success");
        return Result;
    }
    public static <T> Result<T> success(T data){
        Result<T> Result = new Result<>(data);
        Result.setCode("200");
        Result.setMsg("success");
        return Result;
    }
    public static <T> Result<T> success(T data, String msg){
        Result<T> Result = new Result<>(data);
        Result.setCode("200");
        Result.setMsg(msg);
        return Result;
    }
    public static <T> Result<T> success(T data, String msg,String code){
        Result<T> Result = new Result<>(data);
        Result.setCode("200");
        Result.setMsg(msg);
        return Result;
    }
    public static Result error(String code, String msg){
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg(msg);
        return Result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
