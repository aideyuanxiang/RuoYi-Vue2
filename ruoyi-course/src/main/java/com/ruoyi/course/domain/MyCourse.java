package com.ruoyi.course.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息对象 my_course
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public class MyCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程简介 */
    @Excel(name = "课程简介")
    private String courseDesc;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private Long courseScore;

    /** 开课老师信息信息 */
    private List<MyCourseTeach> myCourseTeachList;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setCourseDesc(String courseDesc) 
    {
        this.courseDesc = courseDesc;
    }

    public String getCourseDesc() 
    {
        return courseDesc;
    }
    public void setCourseScore(Long courseScore) 
    {
        this.courseScore = courseScore;
    }

    public Long getCourseScore() 
    {
        return courseScore;
    }

    public List<MyCourseTeach> getMyCourseTeachList()
    {
        return myCourseTeachList;
    }

    public void setMyCourseTeachList(List<MyCourseTeach> myCourseTeachList)
    {
        this.myCourseTeachList = myCourseTeachList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("courseDesc", getCourseDesc())
            .append("courseScore", getCourseScore())
            .append("myCourseTeachList", getMyCourseTeachList())
            .toString();
    }
}
