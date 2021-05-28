package com.example.dao;

import com.example.pojo.BlockHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlockRepository extends JpaRepository<BlockHead, Integer> {

//    @Query("select a from BlockHead a where a.id=:id")   //SPEL表达式
//    BlockHead findBlockHead(@Param("id") String id);        // 参数id 映射到数据库字段id
}
