package com.ruoyi.course.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 开课老师信息对象 my_course_teach
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public class MyCourseTeach extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老师ID */
    private Long teacherId;

    /** 老师名称 */
    @Excel(name = "老师名称")
    private String teacherName;

    /** 老师简介 */
    @Excel(name = "老师简介")
    private String teacherDesc;

    /** 课程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "课程开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 与课程关联 */
    @Excel(name = "与课程关联")
    private Long courseId;

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setTeacherDesc(String teacherDesc) 
    {
        this.teacherDesc = teacherDesc;
    }

    public String getTeacherDesc() 
    {
        return teacherDesc;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherDesc", getTeacherDesc())
            .append("startTime", getStartTime())
            .append("courseId", getCourseId())
            .toString();
    }
}
