package com.ruoyi.travel.mapper;

import java.util.List;
import com.ruoyi.travel.domain.MyTravel;
import com.ruoyi.travel.domain.MyTravelOrder;

/**
 * 旅游信息Mapper接口
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
public interface MyTravelMapper 
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
     * 删除旅游信息
     * 
     * @param travelId 旅游信息主键
     * @return 结果
     */
    public int deleteMyTravelByTravelId(Long travelId);

    /**
     * 批量删除旅游信息
     * 
     * @param travelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMyTravelByTravelIds(Long[] travelIds);

    /**
     * 批量删除旅游订单
     * 
     * @param travelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMyTravelOrderByTravelIds(Long[] travelIds);
    
    /**
     * 批量新增旅游订单
     * 
     * @param myTravelOrderList 旅游订单列表
     * @return 结果
     */
    public int batchMyTravelOrder(List<MyTravelOrder> myTravelOrderList);
    

    /**
     * 通过旅游信息主键删除旅游订单信息
     * 
     * @param travelId 旅游信息ID
     * @return 结果
     */
    public int deleteMyTravelOrderByTravelId(Long travelId);
}
