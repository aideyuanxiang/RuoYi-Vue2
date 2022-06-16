package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.domain.MyBook;
import com.ruoyi.system.service.IMyBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RuoYiApplication.class)
public class MyBookServiceImplTest {

    @Autowired
    private IMyBookService myBookService;

    @Test
    public void findAll(){

        List<MyBook> list = myBookService.list();

        list.forEach(System.out::println);
    }

    @Test
    public void findBookName(){

        List<MyBook> book_name = myBookService.list(new QueryWrapper<MyBook>().eq("book_name", "《》"));

        book_name.forEach(System.out::println);
    }

    @Test
    public void insertBook(){

        MyBook myBook=new MyBook();
        myBook.setBookName("《xxxx》");
        myBook.setBookPrice(80d);
        myBook.setBookDesc("xx");
        boolean save=myBookService.save(myBook);
        System.out.println(save);
    }

    @Test
    public void updateBook(){
        MyBook myBook=new MyBook();
        myBook.setBookId(5);
        myBook.setBookName("《xxxx2》");
        myBook.setBookPrice(80d);
        myBook.setBookDesc("xxx");

        boolean result=myBookService.updateById(myBook);
        System.out.println(result);
    }

    @Test
    public void deleteById(){

        boolean b =myBookService.removeById(5);
        System.out.println(b);
    }
}