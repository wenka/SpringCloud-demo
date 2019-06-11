package com.demo.springboot.springboot.dynamic.datasource.demo.config;

import com.demo.springboot.springboot.dynamic.datasource.demo.model.DataSourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 02:58
 * Description:初始化数据源
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    //指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    /**
     * 主数据源
     */
    private DataSource defaultDataSource;

    /**
     * 从数据源
     */
    private Map<String, DataSource> slaveDataSources;

    @Override
    public void setEnvironment(Environment environment) {
        this.initMainDS(environment);
        this.initSlaveDS(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put("datasource", this.defaultDataSource);
        targetDataSources.putAll(this.slaveDataSources);
        DynamicDataSourceContextHolder.dataSourceIds.add("datasource");
        for (String slaveKey : this.slaveDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(slaveKey);
        }

        // 创建动态数据源
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(DynamicDataSource.class);
        genericBeanDefinition.setSynthetic(true);
        MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        propertyValues.addPropertyValue("targetDataSources", targetDataSources);
        beanDefinitionRegistry.registerBeanDefinition("dataSource", genericBeanDefinition);

        LOGGER.info("动态数据源注册完成！");
    }

    /**
     * 初始化主数据源
     *
     * @param environment
     */
    private void initMainDS(Environment environment) {
        LOGGER.info("初始化主数据源 begin");
        String driver = environment.getProperty("spring.datasource.driverClassName");
        String url = environment.getProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
        DataSourceVo dataSourceVo = new DataSourceVo()
                .setDriver(driver)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password);
        this.defaultDataSource = this.buildDataSrouce(dataSourceVo);
        LOGGER.info("初始化主数据源 end");
    }

    /**
     * 初始化从数据源
     *
     * @param environment
     */
    private void initSlaveDS(Environment environment) {
        LOGGER.info("初始化从数据源 begin");
        this.slaveDataSources = new HashMap<>();
        String slaveNames = environment.getProperty("slave.datasource.names");
        for (String slave : slaveNames.split(",")) {
            String driver = environment.getProperty("slave.datasource." + slave + ".driverClassName");
            String url = environment.getProperty("slave.datasource." + slave + ".url");
            String username = environment.getProperty("slave.datasource." + slave + ".username");
            String password = environment.getProperty("slave.datasource." + slave + ".password");
            DataSourceVo dataSourceVo = new DataSourceVo()
                    .setDriver(driver)
                    .setUrl(url)
                    .setUsername(username)
                    .setPassword(password);
            this.slaveDataSources.put(slave, this.buildDataSrouce(dataSourceVo));
        }
        LOGGER.info("初始化从数据源 end");
    }

    /**
     * 构建数据源
     *
     * @param dataSourceVo
     * @return
     */
    private DataSource buildDataSrouce(DataSourceVo dataSourceVo) {
        String type = dataSourceVo.getType();
        if (!StringUtils.hasText(type)) {
            type = DATASOURCE_TYPE_DEFAULT;
        }
        try {
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(type);
            String driver = dataSourceVo.getDriver();
            String url = dataSourceVo.getUrl();
            String username = dataSourceVo.getUsername();
            String password = dataSourceVo.getPassword();
            DataSourceBuilder<? extends DataSource> builder = DataSourceBuilder.create()
                    .type(dataSourceType)
                    .url(url)
                    .driverClassName(driver)
                    .username(username)
                    .password(password);

            return builder.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
