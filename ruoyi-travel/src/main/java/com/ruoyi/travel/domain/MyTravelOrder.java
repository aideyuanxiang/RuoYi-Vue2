package com.ruoyi.travel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 旅游订单对象 my_travel_order
 * 
 * @author yuanxiang
 * @date 2022-07-19
 */
public class MyTravelOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long orderId;

    /** 旅游id */
    @Excel(name = "旅游id")
    private Long travelId;

    /** 支付人 */
    @Excel(name = "支付人")
    private String payedBy;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payedDate;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private Long payedMoney;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setTravelId(Long travelId) 
    {
        this.travelId = travelId;
    }

    public Long getTravelId() 
    {
        return travelId;
    }
    public void setPayedBy(String payedBy) 
    {
        this.payedBy = payedBy;
    }

    public String getPayedBy() 
    {
        return payedBy;
    }
    public void setPayedDate(Date payedDate) 
    {
        this.payedDate = payedDate;
    }

    public Date getPayedDate() 
    {
        return payedDate;
    }
    public void setPayedMoney(Long payedMoney) 
    {
        this.payedMoney = payedMoney;
    }

    public Long getPayedMoney() 
    {
        return payedMoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("travelId", getTravelId())
            .append("payedBy", getPayedBy())
            .append("payedDate", getPayedDate())
            .append("payedMoney", getPayedMoney())
            .toString();
    }
}
