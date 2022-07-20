package com.ruoyi.course.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.course.domain.MyCourseTeach;
import com.ruoyi.course.mapper.MyCourseMapper;
import com.ruoyi.course.domain.MyCourse;
import com.ruoyi.course.service.IMyCourseService;

/**
 * 课程信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@Service
public class MyCourseServiceImpl implements IMyCourseService 
{
    @Autowired
    private MyCourseMapper myCourseMapper;

    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    @Override
    public MyCourse selectMyCourseByCourseId(Long courseId)
    {
        return myCourseMapper.selectMyCourseByCourseId(courseId);
    }

    /**
     * 查询课程信息列表
     * 
     * @param myCourse 课程信息
     * @return 课程信息
     */
    @Override
    public List<MyCourse> selectMyCourseList(MyCourse myCourse)
    {
        return myCourseMapper.selectMyCourseList(myCourse);
    }

    /**
     * 新增课程信息
     * 
     * @param myCourse 课程信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMyCourse(MyCourse myCourse)
    {
        int rows = myCourseMapper.insertMyCourse(myCourse);
        insertMyCourseTeach(myCourse);
        return rows;
    }

    /**
     * 修改课程信息
     * 
     * @param myCourse 课程信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMyCourse(MyCourse myCourse)
    {
        myCourseMapper.deleteMyCourseTeachByCourseId(myCourse.getCourseId());
        insertMyCourseTeach(myCourse);
        return myCourseMapper.updateMyCourse(myCourse);
    }

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的课程信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMyCourseByCourseIds(Long[] courseIds)
    {
        myCourseMapper.deleteMyCourseTeachByCourseIds(courseIds);
        return myCourseMapper.deleteMyCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程信息信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMyCourseByCourseId(Long courseId)
    {
        myCourseMapper.deleteMyCourseTeachByCourseId(courseId);
        return myCourseMapper.deleteMyCourseByCourseId(courseId);
    }

    /**
     * 新增开课老师信息信息
     * 
     * @param myCourse 课程信息对象
     */
    public void insertMyCourseTeach(MyCourse myCourse)
    {
        List<MyCourseTeach> myCourseTeachList = myCourse.getMyCourseTeachList();
        Long courseId = myCourse.getCourseId();
        if (StringUtils.isNotNull(myCourseTeachList))
        {
            List<MyCourseTeach> list = new ArrayList<MyCourseTeach>();
            for (MyCourseTeach myCourseTeach : myCourseTeachList)
            {
                myCourseTeach.setCourseId(courseId);
                list.add(myCourseTeach);
            }
            if (list.size() > 0)
            {
                myCourseMapper.batchMyCourseTeach(list);
            }
        }
    }
}
