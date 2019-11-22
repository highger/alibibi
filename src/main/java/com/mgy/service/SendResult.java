package com.mgy.service;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgy on 2019/11/22
 */
@Data
public class SendResult {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;

    public String toJSONString() {
        Map<String, Object> items = new HashMap<>(3);
        items.put("errorCode", errorCode);
        items.put("errorMsg", errorMsg);
        items.put("success", success);
        return JSON.toJSONString(items);
    }
}
