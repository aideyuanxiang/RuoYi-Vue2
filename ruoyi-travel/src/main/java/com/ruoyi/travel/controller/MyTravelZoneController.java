package com.ruoyi.travel.controller;

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
import com.ruoyi.travel.domain.MyTravelZone;
import com.ruoyi.travel.service.IMyTravelZoneService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 全国通用地区码Controller
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
@RestController
@RequestMapping("/travel/zone")
public class MyTravelZoneController extends BaseController
{
    @Autowired
    private IMyTravelZoneService myTravelZoneService;

    /**
     * 查询全国通用地区码列表
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:list')")
    @GetMapping("/list")
    public AjaxResult list(MyTravelZone myTravelZone)
    {
        List<MyTravelZone> list = myTravelZoneService.selectMyTravelZoneList(myTravelZone);
        return AjaxResult.success(list);
    }

    /**
     * 导出全国通用地区码列表
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:export')")
    @Log(title = "全国通用地区码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MyTravelZone myTravelZone)
    {
        List<MyTravelZone> list = myTravelZoneService.selectMyTravelZoneList(myTravelZone);
        ExcelUtil<MyTravelZone> util = new ExcelUtil<MyTravelZone>(MyTravelZone.class);
        util.exportExcel(response, list, "全国通用地区码数据");
    }

    /**
     * 获取全国通用地区码详细信息
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:query')")
    @GetMapping(value = "/{zoneId}")
    public AjaxResult getInfo(@PathVariable("zoneId") Long zoneId)
    {
        return AjaxResult.success(myTravelZoneService.selectMyTravelZoneByZoneId(zoneId));
    }

    /**
     * 新增全国通用地区码
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:add')")
    @Log(title = "全国通用地区码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyTravelZone myTravelZone)
    {
        return toAjax(myTravelZoneService.insertMyTravelZone(myTravelZone));
    }

    /**
     * 修改全国通用地区码
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:edit')")
    @Log(title = "全国通用地区码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyTravelZone myTravelZone)
    {
        return toAjax(myTravelZoneService.updateMyTravelZone(myTravelZone));
    }

    /**
     * 删除全国通用地区码
     */
    @PreAuthorize("@ss.hasPermi('travel:zone:remove')")
    @Log(title = "全国通用地区码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{zoneIds}")
    public AjaxResult remove(@PathVariable Long[] zoneIds)
    {
        return toAjax(myTravelZoneService.deleteMyTravelZoneByZoneIds(zoneIds));
    }
}
