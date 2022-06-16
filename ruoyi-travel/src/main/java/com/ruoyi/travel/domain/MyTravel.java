package com.ruoyi.travel.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 旅游信息对象 my_travel
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
public class MyTravel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long travelId;

    /** 旅游名城 */
    @Excel(name = "旅游名城")
    private String travelName;

    /** 目的地 */
    @Excel(name = "目的地")
    private String destination;

    /** 出发地 */
    @Excel(name = "出发地")
    private String departure;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 交通工具 */
    @Excel(name = "交通工具")
    private String travelTransform;

    /** 旅游订单信息 */
    private List<MyTravelOrder> myTravelOrderList;

    public void setTravelId(Long travelId) 
    {
        this.travelId = travelId;
    }

    public Long getTravelId() 
    {
        return travelId;
    }
    public void setTravelName(String travelName) 
    {
        this.travelName = travelName;
    }

    public String getTravelName() 
    {
        return travelName;
    }
    public void setDestination(String destination) 
    {
        this.destination = destination;
    }

    public String getDestination() 
    {
        return destination;
    }
    public void setDeparture(String departure) 
    {
        this.departure = departure;
    }

    public String getDeparture() 
    {
        return departure;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setTravelTransform(String travelTransform) 
    {
        this.travelTransform = travelTransform;
    }

    public String getTravelTransform() 
    {
        return travelTransform;
    }

    public List<MyTravelOrder> getMyTravelOrderList()
    {
        return myTravelOrderList;
    }

    public void setMyTravelOrderList(List<MyTravelOrder> myTravelOrderList)
    {
        this.myTravelOrderList = myTravelOrderList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("travelId", getTravelId())
            .append("travelName", getTravelName())
            .append("destination", getDestination())
            .append("departure", getDeparture())
            .append("price", getPrice())
            .append("startDate", getStartDate())
            .append("travelTransform", getTravelTransform())
            .append("myTravelOrderList", getMyTravelOrderList())
            .toString();
    }
}
