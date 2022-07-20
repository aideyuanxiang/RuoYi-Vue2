package com.ruoyi.course.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.course.mapper.MyChooseCourseMapper;
import com.ruoyi.course.domain.MyChooseCourse;
import com.ruoyi.course.service.IMyChooseCourseService;

/**
 * 选课信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@Service
public class MyChooseCourseServiceImpl implements IMyChooseCourseService 
{
    @Autowired
    private MyChooseCourseMapper myChooseCourseMapper;

    /**
     * 查询选课信息
     * 
     * @param chooseId 选课信息主键
     * @return 选课信息
     */
    @Override
    public MyChooseCourse selectMyChooseCourseByChooseId(Long chooseId)
    {
        return myChooseCourseMapper.selectMyChooseCourseByChooseId(chooseId);
    }

    /**
     * 查询选课信息列表
     * 
     * @param myChooseCourse 选课信息
     * @return 选课信息
     */
    @Override
    public List<MyChooseCourse> selectMyChooseCourseList(MyChooseCourse myChooseCourse)
    {
        return myChooseCourseMapper.selectMyChooseCourseList(myChooseCourse);
    }

    /**
     * 新增选课信息
     * 
     * @param myChooseCourse 选课信息
     * @return 结果
     */
    @Override
    public int insertMyChooseCourse(MyChooseCourse myChooseCourse)
    {
        return myChooseCourseMapper.insertMyChooseCourse(myChooseCourse);
    }

    /**
     * 修改选课信息
     * 
     * @param myChooseCourse 选课信息
     * @return 结果
     */
    @Override
    public int updateMyChooseCourse(MyChooseCourse myChooseCourse)
    {
        return myChooseCourseMapper.updateMyChooseCourse(myChooseCourse);
    }

    /**
     * 批量删除选课信息
     * 
     * @param chooseIds 需要删除的选课信息主键
     * @return 结果
     */
    @Override
    public int deleteMyChooseCourseByChooseIds(Long[] chooseIds)
    {
        return myChooseCourseMapper.deleteMyChooseCourseByChooseIds(chooseIds);
    }

    /**
     * 删除选课信息信息
     * 
     * @param chooseId 选课信息主键
     * @return 结果
     */
    @Override
    public int deleteMyChooseCourseByChooseId(Long chooseId)
    {
        return myChooseCourseMapper.deleteMyChooseCourseByChooseId(chooseId);
    }
}
