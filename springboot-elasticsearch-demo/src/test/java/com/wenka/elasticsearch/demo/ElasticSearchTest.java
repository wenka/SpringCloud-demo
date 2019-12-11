package com.wenka.elasticsearch.demo;

import com.wenka.elasticsearch.demo.dao.EmployeeDao;
import com.wenka.elasticsearch.demo.model.Employee;
import com.wenka.elasticsearch.demo.util.DateUtil;
import org.elasticsearch.index.query.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/09  上午 11:06
 * @description:
 */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSave() {
        Employee a = new Employee()
                .setId("1")
                .setFirstName("A")
                .setLastName("aa")
                .setAbout("I am aa")
                .setAge(22)
                .setInterests(Arrays.asList("singing", "dancing"))
                .setCreateTime(DateUtil.getDate(2019, 2, 1));

        Employee b = new Employee()
                .setId("2")
                .setFirstName("B")
                .setLastName("bb")
                .setAbout("I am bb")
                .setAge(28)
                .setInterests(Arrays.asList("sports", "music"))
                .setCreateTime(DateUtil.getDate(2019, 4, 3));

        Employee c = new Employee()
                .setId("3")
                .setFirstName("C")
                .setLastName("cc")
                .setAbout("I am cc")
                .setAge(18)
                .setInterests(Arrays.asList("BalaBala", "dancing"))
                .setCreateTime(DateUtil.getDate(2019, 6, 5));
        List<Employee> employees = Arrays.asList(a, b, c);
        Iterable<Employee> save = this.employeeDao.saveAll(employees);
        System.out.println(save);
    }

    @Test
    public void testAll() {
        Iterable<Employee> all = this.employeeDao.findAll();
        Iterator<Employee> iterator = all.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testById(){
        Optional<Employee> byId = this.employeeDao.findById("1");
        if (byId.isPresent()){
            System.out.println(byId.get());
        }else {
            System.out.println("noting!");
        }
    }

    @Test
    public void testSelect(){
        BoolQueryBuilder query = new BoolQueryBuilder();
//        query.must(new TermQueryBuilder("age",28));
        query.must(new MatchQueryBuilder("about","*am*"))
        .must(new RangeQueryBuilder("age").gt("20"));
        Iterable<Employee> search = this.employeeDao.search(query);
        Iterator<Employee> iterator = search.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
