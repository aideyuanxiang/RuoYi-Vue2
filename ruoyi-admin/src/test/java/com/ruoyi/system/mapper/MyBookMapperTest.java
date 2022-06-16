package com.ruoyi.system.mapper;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.domain.MyBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class MyBookMapperTest {

    @Autowired
    private MyBookMapper myBookMapper;

    @org.junit.jupiter.api.Test
    void findAll() {


    }

    @Test
    void findByBookId() {
        MyBook myBook = myBookMapper.findByBookId(1);
        System.out.println(myBook);
    }
}