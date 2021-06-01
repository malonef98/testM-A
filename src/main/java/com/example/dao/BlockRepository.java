package com.example.dao;

import com.example.domain.entity.BlockHead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockHead, Integer> {

//    @Query("select a from BlockHead a where a.id=:id")   //SPEL表达式
//    BlockHead findBlockHead(@Param("id") String id);        // 参数id 映射到数据库字段id

      BlockHead findByFee(String fee);

      BlockHead findByHeight(int height);

}
