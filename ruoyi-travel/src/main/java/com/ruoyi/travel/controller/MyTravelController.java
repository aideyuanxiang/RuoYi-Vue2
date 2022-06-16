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
import com.ruoyi.travel.domain.MyTravel;
import com.ruoyi.travel.service.IMyTravelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 旅游信息Controller
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
@RestController
@RequestMapping("/travel/travel")
public class MyTravelController extends BaseController
{
    @Autowired
    private IMyTravelService myTravelService;

    /**
     * 查询旅游信息列表
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:list')")
    @GetMapping("/list")
    public TableDataInfo list(MyTravel myTravel)
    {
        startPage();
        List<MyTravel> list = myTravelService.selectMyTravelList(myTravel);
        return getDataTable(list);
    }

    /**
     * 导出旅游信息列表
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:export')")
    @Log(title = "旅游信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MyTravel myTravel)
    {
        List<MyTravel> list = myTravelService.selectMyTravelList(myTravel);
        ExcelUtil<MyTravel> util = new ExcelUtil<MyTravel>(MyTravel.class);
        util.exportExcel(response, list, "旅游信息数据");
    }

    /**
     * 获取旅游信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:query')")
    @GetMapping(value = "/{travelId}")
    public AjaxResult getInfo(@PathVariable("travelId") Long travelId)
    {
        return AjaxResult.success(myTravelService.selectMyTravelByTravelId(travelId));
    }

    /**
     * 新增旅游信息
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:add')")
    @Log(title = "旅游信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyTravel myTravel)
    {
        return toAjax(myTravelService.insertMyTravel(myTravel));
    }

    /**
     * 修改旅游信息
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:edit')")
    @Log(title = "旅游信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyTravel myTravel)
    {
        return toAjax(myTravelService.updateMyTravel(myTravel));
    }

    /**
     * 删除旅游信息
     */
    @PreAuthorize("@ss.hasPermi('travel:travel:remove')")
    @Log(title = "旅游信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{travelIds}")
    public AjaxResult remove(@PathVariable Long[] travelIds)
    {
        return toAjax(myTravelService.deleteMyTravelByTravelIds(travelIds));
    }
}
