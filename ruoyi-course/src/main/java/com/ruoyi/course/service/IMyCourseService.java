package com.ruoyi.course.service;

import java.util.List;
import com.ruoyi.course.domain.MyCourse;

/**
 * 课程信息Service接口
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public interface IMyCourseService 
{
    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    public MyCourse selectMyCourseByCourseId(Long courseId);

    /**
     * 查询课程信息列表
     * 
     * @param myCourse 课程信息
     * @return 课程信息集合
     */
    public List<MyCourse> selectMyCourseList(MyCourse myCourse);

    /**
     * 新增课程信息
     * 
     * @param myCourse 课程信息
     * @return 结果
     */
    public int insertMyCourse(MyCourse myCourse);

    /**
     * 修改课程信息
     * 
     * @param myCourse 课程信息
     * @return 结果
     */
    public int updateMyCourse(MyCourse myCourse);

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的课程信息主键集合
     * @return 结果
     */
    public int deleteMyCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程信息信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    public int deleteMyCourseByCourseId(Long courseId);
}
