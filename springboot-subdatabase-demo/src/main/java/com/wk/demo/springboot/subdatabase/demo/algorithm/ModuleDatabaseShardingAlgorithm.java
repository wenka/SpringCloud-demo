package com.wk.demo.springboot.subdatabase.demo.algorithm;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/06  下午 04:25
 * Description:
 */
@Log4j2
public class ModuleDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        log.info("[sharding database] dbNames: {}, shardingValue: {}", availableTargetNames, shardingValue);
        String value = shardingValue.getValue();
        int abs = Math.abs(value.hashCode());
        int i = abs % availableTargetNames.size();
        log.info("id={}, value={}, db={}", value, abs, i);
        for (String tbName : availableTargetNames) {
            if (tbName.endsWith(String.valueOf(i))) {
                return tbName;
            }
        }
        return null;
    }

}
