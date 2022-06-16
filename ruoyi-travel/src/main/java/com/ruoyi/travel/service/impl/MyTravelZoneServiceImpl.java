package com.ruoyi.travel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.travel.mapper.MyTravelZoneMapper;
import com.ruoyi.travel.domain.MyTravelZone;
import com.ruoyi.travel.service.IMyTravelZoneService;

/**
 * 全国通用地区码Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
@Service
public class MyTravelZoneServiceImpl implements IMyTravelZoneService 
{
    @Autowired
    private MyTravelZoneMapper myTravelZoneMapper;

    /**
     * 查询全国通用地区码
     * 
     * @param zoneId 全国通用地区码主键
     * @return 全国通用地区码
     */
    @Override
    public MyTravelZone selectMyTravelZoneByZoneId(Long zoneId)
    {
        return myTravelZoneMapper.selectMyTravelZoneByZoneId(zoneId);
    }

    /**
     * 查询全国通用地区码列表
     * 
     * @param myTravelZone 全国通用地区码
     * @return 全国通用地区码
     */
    @Override
    public List<MyTravelZone> selectMyTravelZoneList(MyTravelZone myTravelZone)
    {
        return myTravelZoneMapper.selectMyTravelZoneList(myTravelZone);
    }

    /**
     * 新增全国通用地区码
     * 
     * @param myTravelZone 全国通用地区码
     * @return 结果
     */
    @Override
    public int insertMyTravelZone(MyTravelZone myTravelZone)
    {
        return myTravelZoneMapper.insertMyTravelZone(myTravelZone);
    }

    /**
     * 修改全国通用地区码
     * 
     * @param myTravelZone 全国通用地区码
     * @return 结果
     */
    @Override
    public int updateMyTravelZone(MyTravelZone myTravelZone)
    {
        return myTravelZoneMapper.updateMyTravelZone(myTravelZone);
    }

    /**
     * 批量删除全国通用地区码
     * 
     * @param zoneIds 需要删除的全国通用地区码主键
     * @return 结果
     */
    @Override
    public int deleteMyTravelZoneByZoneIds(Long[] zoneIds)
    {
        return myTravelZoneMapper.deleteMyTravelZoneByZoneIds(zoneIds);
    }

    /**
     * 删除全国通用地区码信息
     * 
     * @param zoneId 全国通用地区码主键
     * @return 结果
     */
    @Override
    public int deleteMyTravelZoneByZoneId(Long zoneId)
    {
        return myTravelZoneMapper.deleteMyTravelZoneByZoneId(zoneId);
    }
}
