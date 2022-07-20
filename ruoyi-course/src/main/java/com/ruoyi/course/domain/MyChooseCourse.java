package com.ruoyi.course.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 选课信息对象 my_choose_course
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public class MyChooseCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选课信息ID */
    private Long chooseId;

    /** 老师名称 */
    @Excel(name = "老师名称")
    private String teacherName;

    /** 课程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "课程开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 老师ID */
    @Excel(name = "老师ID")
    private Long teacherId;

    public void setChooseId(Long chooseId) 
    {
        this.chooseId = chooseId;
    }

    public Long getChooseId() 
    {
        return chooseId;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("chooseId", getChooseId())
            .append("teacherName", getTeacherName())
            .append("startTime", getStartTime())
            .append("courseId", getCourseId())
            .append("userId", getUserId())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
