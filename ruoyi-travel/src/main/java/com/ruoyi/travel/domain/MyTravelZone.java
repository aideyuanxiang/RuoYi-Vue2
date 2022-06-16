package com.ruoyi.travel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 全国通用地区码对象 my_travel_zone
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
public class MyTravelZone extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long zoneId;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String zoneName;

    /** 所属上级规划 */
    @Excel(name = "所属上级规划")
    private Long preZoneId;

    /** 省市县 */
    @Excel(name = "省市县")
    private Long zoneType;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 区域代码 */
    @Excel(name = "区域代码")
    private Long zoneCode;

    public void setZoneId(Long zoneId) 
    {
        this.zoneId = zoneId;
    }

    public Long getZoneId() 
    {
        return zoneId;
    }
    public void setZoneName(String zoneName) 
    {
        this.zoneName = zoneName;
    }

    public String getZoneName() 
    {
        return zoneName;
    }
    public void setPreZoneId(Long preZoneId) 
    {
        this.preZoneId = preZoneId;
    }

    public Long getPreZoneId() 
    {
        return preZoneId;
    }
    public void setZoneType(Long zoneType) 
    {
        this.zoneType = zoneType;
    }

    public Long getZoneType() 
    {
        return zoneType;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setZoneCode(Long zoneCode) 
    {
        this.zoneCode = zoneCode;
    }

    public Long getZoneCode() 
    {
        return zoneCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("zoneId", getZoneId())
            .append("zoneName", getZoneName())
            .append("preZoneId", getPreZoneId())
            .append("zoneType", getZoneType())
            .append("createDate", getCreateDate())
            .append("zoneCode", getZoneCode())
            .toString();
    }
}
