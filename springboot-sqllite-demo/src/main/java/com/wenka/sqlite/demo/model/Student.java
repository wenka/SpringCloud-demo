package com.wenka.sqlite.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/04/03  下午 03:27
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "create_time", insertable = true)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime = new Date();

    @OneToMany(mappedBy = "stuId", fetch = FetchType.EAGER)
    private List<Address> addresses;
}
