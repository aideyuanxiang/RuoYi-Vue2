package com.ruoyi.course.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.course.domain.MyChooseCourse;
import com.ruoyi.course.service.IMyChooseCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 选课信息Controller
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@RestController
@RequestMapping("/course/choose")
public class MyChooseCourseController extends BaseController
{
    @Autowired
    private IMyChooseCourseService myChooseCourseService;

    /**
     * 查询选课信息列表
     */
    @PreAuthorize("@ss.hasPermi('course:choose:list')")
    @GetMapping("/list")
    public TableDataInfo list(MyChooseCourse myChooseCourse)
    {
        startPage();
        List<MyChooseCourse> list = myChooseCourseService.selectMyChooseCourseList(myChooseCourse);
        return getDataTable(list);
    }

    /**
     * 导出选课信息列表
     */
    @PreAuthorize("@ss.hasPermi('course:choose:export')")
    @Log(title = "选课信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MyChooseCourse myChooseCourse)
    {
        List<MyChooseCourse> list = myChooseCourseService.selectMyChooseCourseList(myChooseCourse);
        ExcelUtil<MyChooseCourse> util = new ExcelUtil<MyChooseCourse>(MyChooseCourse.class);
        util.exportExcel(response, list, "选课信息数据");
    }

    /**
     * 获取选课信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:choose:query')")
    @GetMapping(value = "/{chooseId}")
    public AjaxResult getInfo(@PathVariable("chooseId") Long chooseId)
    {
        return AjaxResult.success(myChooseCourseService.selectMyChooseCourseByChooseId(chooseId));
    }

    /**
     * 新增选课信息
     */
    @PreAuthorize("@ss.hasPermi('course:choose:add')")
    @Log(title = "选课信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyChooseCourse myChooseCourse)
    {
        return toAjax(myChooseCourseService.insertMyChooseCourse(myChooseCourse));
    }

    /**
     * 修改选课信息
     */
    @PreAuthorize("@ss.hasPermi('course:choose:edit')")
    @Log(title = "选课信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyChooseCourse myChooseCourse)
    {
        return toAjax(myChooseCourseService.updateMyChooseCourse(myChooseCourse));
    }

    /**
     * 删除选课信息
     */
    @PreAuthorize("@ss.hasPermi('course:choose:remove')")
    @Log(title = "选课信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chooseIds}")
    public AjaxResult remove(@PathVariable Long[] chooseIds)
    {
        return toAjax(myChooseCourseService.deleteMyChooseCourseByChooseIds(chooseIds));
    }
}
