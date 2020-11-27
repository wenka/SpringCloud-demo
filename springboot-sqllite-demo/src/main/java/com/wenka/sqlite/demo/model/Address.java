package com.wenka.sqlite.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/07/20  下午 01:38
 * @description:
 */
@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    private String id;

    private String addr;

    @Column(name = "stu_id")
    private String stuId;
}

