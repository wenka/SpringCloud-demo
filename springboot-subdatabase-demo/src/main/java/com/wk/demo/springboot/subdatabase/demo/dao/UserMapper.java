package com.wk.demo.springboot.subdatabase.demo.dao;

import com.wk.demo.springboot.subdatabase.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/06  下午 04:33
 * Description:
 */
@Repository
public interface UserMapper {

    /**
     * 保存用户单个
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 批量插入用户
     *
     * @param userList
     * @return
     */
    int batchInsertUser(List<User> userList);

    /**
     * 查询全部
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 查询分页全部
     *
     * @return
     */
    List<User> selectAllPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    User selectOne(@Param("id") String id);

    /**
     * 查询通过name
     *
     * @param name
     * @return
     */
    User selectByName(@Param("name") String name);
}
