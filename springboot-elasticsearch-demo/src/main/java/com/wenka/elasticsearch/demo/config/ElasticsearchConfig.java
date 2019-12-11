package com.wenka.elasticsearch.demo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/11  下午 02:20
 * @description:
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.rest.uris}")
    private List<String> uris;
    /**
     *
     */
    @Override
    public RestHighLevelClient elasticsearchClient() {
        String[] strings = new String[uris.size()];
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris.toArray(strings))
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    @Override
    public EntityMapper entityMapper() {

        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(
                elasticsearchMappingContext(), new DefaultConversionService()
        );
        entityMapper.setConversions(elasticsearchCustomConversions());

        return entityMapper;
    }

    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    @Primary
    @Override
    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchRestTemplate(elasticsearchClient(), elasticsearchConverter(), resultsMapper());
    }
}
