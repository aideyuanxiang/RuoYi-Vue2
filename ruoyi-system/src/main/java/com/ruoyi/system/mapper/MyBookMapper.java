package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.MyBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MyBookMapper extends BaseMapper<MyBook> {

    List<MyBook> findAll(MyBook myBook);

    MyBook findByBookId(@Param("bookId") Integer bookId);

    List<Map<String,Object>> findBookSubscribeCount();

    @Insert("insert into my_book(book_name, book_price, book_desc) values(#{bookName}, #{bookPrice}, #{bookDesc})")
    int insertMyBook(MyBook myBook);

    @Update("update my_book set book_name=#{bookName}, book_price=#{bookPrice}, book_desc=#{bookDesc} where book_id = #{bookId}")
    int updateMyBook(MyBook myBook);
}
