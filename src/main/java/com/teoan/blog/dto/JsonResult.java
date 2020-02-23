package com.teoan.blog.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Teoan
 * @description
 * @date 2020/2/19 15:11
 */
public class JsonResult {

    private static Map<String, Object> map = new HashMap<String, Object>(3);

    public static Map<String, Object> fail(String msg, Object data){
        map.clear();
        map.put("code",1);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

    public static Map<String, Object> fail(String msg){
        map.clear();
        map.put("code",1);
        map.put("msg",msg);
        return map;
    }


    public static Map<String, Object> ok(String msg,Object data){
        map.clear();
        map.put("code",0);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }


    public static Map<String, Object> ok(String msg){
        map.clear();
        map.put("code",0);
        map.put("msg",msg);
        return map;
    }
}
