package com.soft.sys;

import com.google.gson.Gson;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 返回结果统一封装
 * @author suphowe
 */
public class Result implements Serializable {

    public static String succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static String succ(int code, String msg, Object data) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return new Gson().toJson(result);
    }

    public static String fail(String msg) {
        return fail(400, msg, null);
    }

    public static String fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static String fail(int code, String msg, Object data) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return new Gson().toJson(result);
    }
}
