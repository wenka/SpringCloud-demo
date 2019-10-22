package com.demo.springbootlockdemo;

import java.util.TreeMap;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/09/12  下午 03:13
 * Description:
 */
public class CacheData {

    public static TreeMap<String, Object> data = new TreeMap<>();

    public static TreeMap getData() {
        return data;
    }
}
