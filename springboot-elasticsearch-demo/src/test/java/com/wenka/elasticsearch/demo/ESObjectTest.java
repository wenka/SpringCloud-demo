package com.wenka.elasticsearch.demo;

import com.wenka.elasticsearch.demo.dao.EmployeeDao;
import com.wenka.elasticsearch.demo.model.Employee;
import com.wenka.elasticsearch.demo.util.DateUtil;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
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
    public void testSaveOne() {
        Employee d = new Employee().setFirstName("E").setLastName("ee").setAbout("I am ee").setAge(23).setGender("女").setInterests(Arrays.asList("study")).setCreateTime(DateUtil.getDate(2019, 8, 7));
        this.employeeDao.save(d);
    }

    @Test
    public void testSave() {
        long begin = System.currentTimeMillis();
        int total = 10000000; // 一千万数据
        List<Employee> employeeList = new ArrayList<>(10000);
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            Employee employee = new Employee()
                    .setFirstName("fn" + i)
                    .setLastName("auto ln" + i)
                    .setAbout("I am " + i)
                    .setAge(random.nextInt(50))
                    .setGender(random.nextBoolean() ? "男" : "女")
//                    .setInterests(Arrays.asList("singing", "dancing"))
                    .setCreateTime(DateUtil.getDate(2019, random.nextInt(12), random.nextInt(28)));
            employeeList.add(employee);
            if (employeeList.size() == 10000) {
                this.employeeDao.saveAll(employeeList);
                employeeList.clear();
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - begin));
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
            System.out.println("------------------------" + keyAsString + "(" + bucket.getDocCount() + ")------------------------");
            Aggregations aggregations = bucket.getAggregations();
            List<Aggregation> aggregationList = aggregations.asList();
            for (Aggregation agg : aggregationList) {
                ParsedTopHits topHits = (ParsedTopHits) agg;
                Iterator<SearchHit> iterator = topHits.getHits().iterator();
                while (iterator.hasNext()) {
                    SearchHit next = iterator.next();
                    Map<String, Object> sourceAsMap = next.getSourceAsMap();
                    System.out.println(sourceAsMap);
                }
            }
        }
    }

    /**
     * 根据日期分组
     *
     * @Param
     * @Return void
     * @Author wenka
     * @Date 2019/12/11 下午 04:27
     */
    @Test
    public void testGroupByDate() {
        // 查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        // 按日期年月份分组
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("date").field("create_time")
                .dateHistogramInterval(DateHistogramInterval.MONTH);
        // 取组内年龄最大者
        TopHitsAggregationBuilder gender = AggregationBuilders.topHits("date").sort("age", SortOrder.DESC).size(1).fetchSource(true);
        dateHistogramAggregationBuilder.subAggregation(gender);

        queryBuilder.addAggregation(dateHistogramAggregationBuilder);

        Aggregations query = this.elasticsearchOperations.query(queryBuilder.build(), response -> {
            return response.getAggregations();
        });

        Map<String, Aggregation> aggregationMap = query.asMap();
        ParsedDateHistogram aggregation = (ParsedDateHistogram) aggregationMap.get("date");
        List<? extends Histogram.Bucket> buckets = aggregation.getBuckets();
        for (Histogram.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("------------------------" + keyAsString + "月（" + bucket.getDocCount() + "）------------------------");
            Aggregations aggregations = bucket.getAggregations();
            List<Aggregation> aggregationList = aggregations.asList();
            for (Aggregation agg : aggregationList) {
                ParsedTopHits topHits = (ParsedTopHits) agg;
                Iterator<SearchHit> iterator = topHits.getHits().iterator();
                while (iterator.hasNext()) {
                    SearchHit next = iterator.next();
                    Map<String, Object> sourceAsMap = next.getSourceAsMap();
                    System.out.println(sourceAsMap);
                }
            }
        }
    }
}
