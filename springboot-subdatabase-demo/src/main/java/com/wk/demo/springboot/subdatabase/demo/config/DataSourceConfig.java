package com.wk.demo.springboot.subdatabase.demo.config;

import com.wk.demo.springboot.subdatabase.demo.algorithm.ModuleDatabaseShardingAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/06  下午 04:08
 * Description:
 */
@Configuration
@Log4j2
public class DataSourceConfig {

    private static final int DS_COUNTS = 2;

    @Bean
    public DataSource build() throws SQLException {
        log.info("build data source.");

        // 收集分库列表
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>(DS_COUNTS);
        for (int i = 0; i < DS_COUNTS; i++) {
            String dbName = "ds" + i;
            DataSource dataSource = this.createDataSource(dbName);
            dataSourceMap.put(dbName, dataSource);
        }

        // 表的分库分表策略
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        tableRuleConfiguration.setLogicTable("user");
        tableRuleConfiguration.setActualDataNodes("ds${0..1}.user");

        // 分库策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuleDatabaseShardingAlgorithm()));

        // 分表策略
//        tableRuleConfiguration.setTableShardingStrategyConfig();

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.setDefaultDataSourceName(dataSourceMap.keySet().iterator().next());
        shardingRuleConfiguration.getTableRuleConfigs().addAll(Arrays.asList(tableRuleConfiguration));

        Properties properties = new Properties();
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, Collections.EMPTY_MAP, properties);
        return dataSource;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        log.info("create data source: {}", dataSourceName);
        HikariDataSource result = new HikariDataSource();
        result.setJdbcUrl(String.format("jdbc:mysql://192.168.80.129:3306/%s", dataSourceName));
        result.setDriverClassName("com.mysql.cj.jdbc.Driver");
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }

}
