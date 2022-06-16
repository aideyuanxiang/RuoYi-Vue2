package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.MyBook;
import com.ruoyi.system.mapper.MyBookMapper;
import com.ruoyi.system.service.IMyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyBookServiceImpl extends ServiceImpl<MyBookMapper,MyBook> implements IMyBookService {

    @Autowired
    private MyBookMapper myBookMapper;

    @Override
    public List<MyBook> findAll(MyBook myBook) {

        return myBookMapper.findAll(myBook);
    }

    @Override
    public MyBook getById(Integer id) {
        return myBookMapper.findByBookId(id);
    }

    @Override
    public Map<String, List<Object>> getBookSubscribeCount() {

        Map<String,List<Object>> result=new HashMap<>();
        List<Map<String,Object>> bookSubscribeCount=myBookMapper.findBookSubscribeCount();

        List<Object> bookNameList = bookSubscribeCount.stream()
                .map(x -> x.get("bookName"))
                .collect(Collectors.toList());

        List<Object> subCountList = bookSubscribeCount.stream()
                .map(x -> x.get("subCount"))
                .collect(Collectors.toList());

        result.put("bookNameList",bookNameList);
        result.put("subscribeCountList",subCountList);

        return result;
    }

    @Override
    public int addMyBook(MyBook myBook) {

        return myBookMapper.insertMyBook(myBook);
    }

    @Override
    public int updateBook(MyBook myBook) {
        return myBookMapper.updateMyBook(myBook);
    }
}
