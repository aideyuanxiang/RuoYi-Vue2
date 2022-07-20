package com.ruoyi.course.mapper;

import java.util.List;
import com.ruoyi.course.domain.MyChooseCourse;

/**
 * 选课信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public interface MyChooseCourseMapper 
{
    /**
     * 查询选课信息
     * 
     * @param chooseId 选课信息主键
     * @return 选课信息
     */
    public MyChooseCourse selectMyChooseCourseByChooseId(Long chooseId);

    /**
     * 查询选课信息列表
     * 
     * @param myChooseCourse 选课信息
     * @return 选课信息集合
     */
    public List<MyChooseCourse> selectMyChooseCourseList(MyChooseCourse myChooseCourse);

    /**
     * 新增选课信息
     * 
     * @param myChooseCourse 选课信息
     * @return 结果
     */
    public int insertMyChooseCourse(MyChooseCourse myChooseCourse);

    /**
     * 修改选课信息
     * 
     * @param myChooseCourse 选课信息
     * @return 结果
     */
    public int updateMyChooseCourse(MyChooseCourse myChooseCourse);

    /**
     * 删除选课信息
     * 
     * @param chooseId 选课信息主键
     * @return 结果
     */
    public int deleteMyChooseCourseByChooseId(Long chooseId);

    /**
     * 批量删除选课信息
     * 
     * @param chooseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMyChooseCourseByChooseIds(Long[] chooseIds);
}
