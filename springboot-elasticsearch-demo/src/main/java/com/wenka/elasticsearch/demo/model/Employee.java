package com.wenka.elasticsearch.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/09  上午 11:11
 * @description:
 */
@Data
@Accessors(chain = true)
@ToString
@Document(indexName = "megacorp", type = "employee")
public class Employee {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "first_name")
    private String firstName;

    @Field(type = FieldType.Text, name = "last_name")
    private String lastName;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text)
    private String about;

    @Field(type = FieldType.Text)
    private List<String> interests;

    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd")
    private Date createTime;

    @Version
    private Long version;
}
