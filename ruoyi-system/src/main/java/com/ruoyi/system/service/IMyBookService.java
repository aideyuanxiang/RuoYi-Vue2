package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.MyBook;

import java.util.List;
import java.util.Map;

public interface IMyBookService extends IService<MyBook> {

    List<MyBook> findAll(MyBook myBook);

    MyBook getById(Integer id);

    Map<String,List<Object>> getBookSubscribeCount();

    int addMyBook(MyBook myBook);

    int updateBook(MyBook myBook);
}
