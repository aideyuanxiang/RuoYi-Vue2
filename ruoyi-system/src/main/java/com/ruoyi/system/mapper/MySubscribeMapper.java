package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.MySubscribe;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySubscribeMapper extends BaseMapper<MySubscribe> {

    @Select("select * from my_subscribe where book_id=#{bookId}")
    List<MySubscribe> findByBookId(@Param("bookId") Integer bookId);
}
