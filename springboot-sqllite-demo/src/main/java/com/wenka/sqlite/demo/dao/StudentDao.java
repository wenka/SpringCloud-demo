package com.wenka.sqlite.demo.dao;

import com.wenka.sqlite.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/04/03  下午 03:30
 * @description:
 */
@Repository
public interface StudentDao extends JpaRepository<Student, String> {
}
