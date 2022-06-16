package com.ruoyi.travel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.travel.domain.MyTravelOrder;
import com.ruoyi.travel.mapper.MyTravelMapper;
import com.ruoyi.travel.domain.MyTravel;
import com.ruoyi.travel.service.IMyTravelService;

/**
 * 旅游信息Service业务层处理
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
@Service
public class MyTravelServiceImpl implements IMyTravelService 
{
    @Autowired
    private MyTravelMapper myTravelMapper;

    /**
     * 查询旅游信息
     * 
     * @param travelId 旅游信息主键
     * @return 旅游信息
     */
    @Override
    public MyTravel selectMyTravelByTravelId(Long travelId)
    {
        return myTravelMapper.selectMyTravelByTravelId(travelId);
    }

    /**
     * 查询旅游信息列表
     * 
     * @param myTravel 旅游信息
     * @return 旅游信息
     */
    @Override
    public List<MyTravel> selectMyTravelList(MyTravel myTravel)
    {
        return myTravelMapper.selectMyTravelList(myTravel);
    }

    /**
     * 新增旅游信息
     * 
     * @param myTravel 旅游信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMyTravel(MyTravel myTravel)
    {
        int rows = myTravelMapper.insertMyTravel(myTravel);
        insertMyTravelOrder(myTravel);
        return rows;
    }

    /**
     * 修改旅游信息
     * 
     * @param myTravel 旅游信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMyTravel(MyTravel myTravel)
    {
        myTravelMapper.deleteMyTravelOrderByTravelId(myTravel.getTravelId());
        insertMyTravelOrder(myTravel);
        return myTravelMapper.updateMyTravel(myTravel);
    }

    /**
     * 批量删除旅游信息
     * 
     * @param travelIds 需要删除的旅游信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMyTravelByTravelIds(Long[] travelIds)
    {
        myTravelMapper.deleteMyTravelOrderByTravelIds(travelIds);
        return myTravelMapper.deleteMyTravelByTravelIds(travelIds);
    }

    /**
     * 删除旅游信息信息
     * 
     * @param travelId 旅游信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMyTravelByTravelId(Long travelId)
    {
        myTravelMapper.deleteMyTravelOrderByTravelId(travelId);
        return myTravelMapper.deleteMyTravelByTravelId(travelId);
    }

    /**
     * 新增旅游订单信息
     * 
     * @param myTravel 旅游信息对象
     */
    public void insertMyTravelOrder(MyTravel myTravel)
    {
        List<MyTravelOrder> myTravelOrderList = myTravel.getMyTravelOrderList();
        Long travelId = myTravel.getTravelId();
        if (StringUtils.isNotNull(myTravelOrderList))
        {
            List<MyTravelOrder> list = new ArrayList<MyTravelOrder>();
            for (MyTravelOrder myTravelOrder : myTravelOrderList)
            {
                myTravelOrder.setTravelId(travelId);
                list.add(myTravelOrder);
            }
            if (list.size() > 0)
            {
                myTravelMapper.batchMyTravelOrder(list);
            }
        }
    }
}
