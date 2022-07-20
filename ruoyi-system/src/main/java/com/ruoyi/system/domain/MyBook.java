package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.annotation.MyXss;
import com.ruoyi.system.annotation.MyXssPrice;
import com.ruoyi.system.validate.Edit;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@TableName("my_book")
public class MyBook {

    @TableId(type = IdType.AUTO)
    @Excel(name = "图书编号",type = Excel.Type.EXPORT,prompt = "书本的唯一编号")
    @NotNull(groups = {Edit.class})
    private Integer bookId;

    @Excel(name = "图书名称")
    @NotBlank(message = "名称不能为空")
    @Size(min=1,max=10,message = "书名长度不符")
    private String bookName;

    @MyXssPrice
    @Excel(name = "图书价格",cellType = Excel.ColumnType.NUMERIC)
    private Double bookPrice;

    @Excel(name = "图书描述")
    private String bookDesc;

    @Excel(name="图书封面",cellType = Excel.ColumnType.IMAGE)
    private String bookUrl;

    @TableField(exist = false)
    @Email(message = "邮箱格式错误")
    private String bookEmail;

    @MyXss
    @TableField(exist = false)
    private String msg;

    private Long belongTo;

    @TableField(exist = false)
    private SysUser sysUser;

    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private List<MySubscribe> subscribes;

    private Integer typeId;

    private String evaluateId;

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<MySubscribe> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(List<MySubscribe> subscribes) {
        this.subscribes = subscribes;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookUrl='" + bookUrl + '\'' +
                '}';
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Long getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Long belongTo) {
        this.belongTo = belongTo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBookEmail() {
        return bookEmail;
    }

    public void setBookEmail(String bookEmail) {
        this.bookEmail = bookEmail;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }
}
