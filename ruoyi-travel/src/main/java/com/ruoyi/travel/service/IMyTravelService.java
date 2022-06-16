package com.ruoyi.travel.service;

import java.util.List;
import com.ruoyi.travel.domain.MyTravel;

/**
 * 旅游信息Service接口
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
public interface IMyTravelService 
{
    /**
     * 查询旅游信息
     * 
     * @param travelId 旅游信息主键
     * @return 旅游信息
     */
    public MyTravel selectMyTravelByTravelId(Long travelId);

    /**
     * 查询旅游信息列表
     * 
     * @param myTravel 旅游信息
     * @return 旅游信息集合
     */
    public List<MyTravel> selectMyTravelList(MyTravel myTravel);

    /**
     * 新增旅游信息
     * 
     * @param myTravel 旅游信息
     * @return 结果
     */
    public int insertMyTravel(MyTravel myTravel);

    /**
     * 修改旅游信息
     * 
     * @param myTravel 旅游信息
     * @return 结果
     */
    public int updateMyTravel(MyTravel myTravel);

    /**
     * 批量删除旅游信息
     * 
     * @param travelIds 需要删除的旅游信息主键集合
     * @return 结果
     */
    public int deleteMyTravelByTravelIds(Long[] travelIds);

    /**
     * 删除旅游信息信息
     * 
     * @param travelId 旅游信息主键
     * @return 结果
     */
    public int deleteMyTravelByTravelId(Long travelId);
}
