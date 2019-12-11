package com.wenka.elasticsearch.demo;

import com.wenka.elasticsearch.demo.dao.EmployeeDao;
import com.wenka.elasticsearch.demo.model.Employee;
import com.wenka.elasticsearch.demo.util.DateUtil;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.ParsedAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
public class ESObjectTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testSave() {
        Employee a = new Employee().setId("1").setFirstName("A").setLastName("aa").setAbout("I am aa").setAge(22).setGender("男").setInterests(Arrays.asList("singing", "dancing")).setCreateTime(DateUtil.getDate(2019, 2, 1));
        Employee b = new Employee().setId("2").setFirstName("B").setLastName("bb").setAbout("I am bb").setAge(28).setGender("女").setInterests(Arrays.asList("sports", "music")).setCreateTime(DateUtil.getDate(2019, 4, 3));
        Employee c = new Employee().setId("3").setFirstName("C").setLastName("cc").setAbout("I am cc").setAge(18).setGender("男").setInterests(Arrays.asList("BalaBala", "dancing")).setCreateTime(DateUtil.getDate(2019, 6, 5));
        Employee d = new Employee().setId("4").setFirstName("D").setLastName("dd").setAbout("I am dd").setAge(25).setGender("女").setInterests(Arrays.asList("study")).setCreateTime(DateUtil.getDate(2019, 8, 7));

        List<Employee> employees = Arrays.asList(a, b, c, d);
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
    public void testById() {
        Optional<Employee> byId = this.employeeDao.findById("1");
        if (byId.isPresent()) {
            System.out.println(byId.get());
        } else {
            System.out.println("noting!");
        }
    }

    @Test
    public void testSelect() {
        BoolQueryBuilder query = new BoolQueryBuilder();
//        query.must(new TermQueryBuilder("age",28));
        query.must(new MatchQueryBuilder("about", "*am*"))
                .must(new RangeQueryBuilder("age").gt("20"));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(query);
        nativeSearchQuery.addSort(Sort.by("age").descending());
        Iterable<Employee> search = this.employeeDao.search(nativeSearchQuery);

        Iterator<Employee> iterator = search.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 分组查询
     *
     * @Param
     * @Return void
     * @Author wenka
     * @Date 2019/12/11 下午 02:56
     */
    @Test
    public void testGroup() {
        // 查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        // 按性别分组
        TermsAggregationBuilder genderTerm = AggregationBuilders.terms("gender_term").field("gender");
        // 取组内年龄最大者
        TopHitsAggregationBuilder gender = AggregationBuilders.topHits("gender_term").sort("age", SortOrder.DESC).size(1).fetchSource(true);
        genderTerm.subAggregation(gender);

        queryBuilder.addAggregation(genderTerm);

        Aggregations query = this.elasticsearchOperations.query(queryBuilder.build(), response -> {
            return response.getAggregations();
        });

        Map<String, Aggregation> aggregationMap = query.asMap();
        ParsedStringTerms aggregation = (ParsedStringTerms) aggregationMap.get("gender_term");
        List<? extends Terms.Bucket> buckets = aggregation.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("------------------------" + keyAsString + "------------------------");
            Aggregations aggregations = bucket.getAggregations();
            List<Aggregation> aggregationList = aggregations.asList();
            for (Aggregation agg : aggregationList) {
                ParsedTopHits topHits = (ParsedTopHits) agg;
                Iterator<SearchHit> iterator = topHits.getHits().iterator();
                while (iterator.hasNext()){
                    SearchHit next = iterator.next();
                    Map<String, Object> sourceAsMap = next.getSourceAsMap();
                    System.out.println(sourceAsMap);
                }
            }
        }
        System.out.println();
    }

}
