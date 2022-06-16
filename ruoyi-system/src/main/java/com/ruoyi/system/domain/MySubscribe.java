package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@TableName("my_subscribe")
public class MySubscribe {

    @TableId(type = IdType.AUTO)
    private Integer subscribeId;

    private Integer bookId;

    private String subscriber;

    @JsonFormat(pattern = "yyyy年MM月dd HH:mm:ss")
    private Date subscribeTime;

    private Integer subscribeStar;


    @Override
    public String toString() {
        return "MySubscribe{" +
                "subscribeId=" + subscribeId +
                ", bookId=" + bookId +
                ", subscriber='" + subscriber + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", subscribeStart=" + subscribeStar +
                '}';
    }



    public Integer getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Integer subscribeId) {
        this.subscribeId = subscribeId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Integer getSubscribeStar() {
        return subscribeStar;
    }

    public void setSubscribeStar(Integer subscribeStar) {
        this.subscribeStar = subscribeStar;
    }
}
