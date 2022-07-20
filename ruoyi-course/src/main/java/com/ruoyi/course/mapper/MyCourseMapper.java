package com.ruoyi.course.mapper;

import java.util.List;
import com.ruoyi.course.domain.MyCourse;
import com.ruoyi.course.domain.MyCourseTeach;

/**
 * 课程信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public interface MyCourseMapper 
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
     * 删除课程信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    public int deleteMyCourseByCourseId(Long courseId);

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMyCourseByCourseIds(Long[] courseIds);

    /**
     * 批量删除开课老师信息
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMyCourseTeachByCourseIds(Long[] courseIds);
    
    /**
     * 批量新增开课老师信息
     * 
     * @param myCourseTeachList 开课老师信息列表
     * @return 结果
     */
    public int batchMyCourseTeach(List<MyCourseTeach> myCourseTeachList);
    

    /**
     * 通过课程信息主键删除开课老师信息信息
     * 
     * @param courseId 课程信息ID
     * @return 结果
     */
    public int deleteMyCourseTeachByCourseId(Long courseId);
}
