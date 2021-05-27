package com.example.test.dao;

import com.example.test.model.BlockHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlockRepository extends JpaRepository<BlockHead, Integer> {
    //自定义repository。手写sql
    @Query(value = "update user set name=?1 where id=?4",nativeQuery = true)   //占位符传值形式
    @Modifying
    int updateById(String name,int id);

    @Query("from User u where u.username=:username")   //SPEL表达式
    BlockHead findUser(@Param("username") String username);// 参数username 映射到数据库字段username
}
