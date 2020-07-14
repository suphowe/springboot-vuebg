package com.soft.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Page implements Serializable {

    Object records;
    int total;
    int size;
    int current;
    Object orders;
    boolean searchCount;
    int pages;

    public Page(Object records, int total, int size, int current, Object orders, boolean searchCount, int pages){
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
        this.orders = orders;
        this.searchCount = searchCount;
        this.pages = pages;
        putData(records, total, size, current, orders, false, pages);
    }

    public HashMap<String, Object> putData(Object records, int total, int size, int current, Object orders, boolean searchCount, int pages){
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("records", records);
        result.put("total", total);
        result.put("size", size);
        result.put("current", current);
        result.put("orders", orders);
        result.put("searchCount", searchCount);
        result.put("pages", pages);
        return result;
    }

}
