package com.ruoyi.travel.service;

import java.util.List;
import com.ruoyi.travel.domain.MyTravelZone;

/**
 * 全国通用地区码Service接口
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
public interface IMyTravelZoneService 
{
    /**
     * 查询全国通用地区码
     * 
     * @param zoneId 全国通用地区码主键
     * @return 全国通用地区码
     */
    public MyTravelZone selectMyTravelZoneByZoneId(Long zoneId);

    /**
     * 查询全国通用地区码列表
     * 
     * @param myTravelZone 全国通用地区码
     * @return 全国通用地区码集合
     */
    public List<MyTravelZone> selectMyTravelZoneList(MyTravelZone myTravelZone);

    /**
     * 新增全国通用地区码
     * 
     * @param myTravelZone 全国通用地区码
     * @return 结果
     */
    public int insertMyTravelZone(MyTravelZone myTravelZone);

    /**
     * 修改全国通用地区码
     * 
     * @param myTravelZone 全国通用地区码
     * @return 结果
     */
    public int updateMyTravelZone(MyTravelZone myTravelZone);

    /**
     * 批量删除全国通用地区码
     * 
     * @param zoneIds 需要删除的全国通用地区码主键集合
     * @return 结果
     */
    public int deleteMyTravelZoneByZoneIds(Long[] zoneIds);

    /**
     * 删除全国通用地区码信息
     * 
     * @param zoneId 全国通用地区码主键
     * @return 结果
     */
    public int deleteMyTravelZoneByZoneId(Long zoneId);
}
