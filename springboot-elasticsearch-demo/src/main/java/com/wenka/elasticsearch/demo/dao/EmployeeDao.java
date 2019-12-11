package com.wenka.elasticsearch.demo.dao;

import com.wenka.elasticsearch.demo.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/09  上午 11:14
 * @description:
 */
public interface EmployeeDao extends ElasticsearchRepository<Employee, String> {
}
