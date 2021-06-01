package com.example.dao;

import com.example.domain.entity.BlockHeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockHeadEntity, Integer> {

//    @Query("select a from BlockHead a where a.id=:id")   //SPEL表达式
//    BlockHead findBlockHead(@Param("id") String id);        // 参数id 映射到数据库字段id

      BlockHeadEntity findByFee(String fee);

      BlockHeadEntity findByHeight(int height);

}
