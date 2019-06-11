package com.demo.springboot.springboot.dynamic.datasource.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 03:22
 * Description: 动态数据源上下文管理
 */
public class DynamicDataSourceContextHolder {

    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * 存放当前线程使用的数据源类型信息
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 存放数据源id
     */
    public static List<String> dataSourceIds = new ArrayList<String>();


    /**
     * 设置数据源
     *
     * @param datasourceType
     */
    public static void setDatasourceType(String datasourceType) {
        contextHolder.set(datasourceType);
    }

    /**
     * 获取数据源
     */
    public static String getDatasourceType() {
        return contextHolder.get();
    }

    /**
     * 判断数据源是否存在
     *
     * @param datasourceType
     */
    public static boolean isContainsDataSource(String datasourceType) {
        return dataSourceIds.contains(datasourceType);
    }

    /**
     * 清除数据源
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
