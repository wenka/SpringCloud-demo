package com.wenka.elasticsearch.demo;

import com.wenka.elasticsearch.demo.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/11  下午 01:57
 * @description:
 */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class ESIndexTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * 删除索引
     *
     * @Param
     * @Return
     * @Author wenka
     * @Date 2019/12/11 下午 01:57
     */
    @Test
    public void delIndex() {
        boolean b = elasticsearchOperations.deleteIndex(Employee.class);
        System.out.println(b);
    }

    /**
     * 创建索引
     *
     * @Param
     * @Return void
     * @Author wenka
     * @Date 2019/12/11 下午 02:34
     */
    @Test
    public void createIndex() {
        boolean index = this.elasticsearchOperations.createIndex(Employee.class);
        System.out.println(index);
    }
}
