package com.example.demo.POJO;

/**
 构造Good实体类，存储goodlist表信息。
    OrderNumber:订单号
    ArriveTime:物品到达时间
    Destination:目的地
    isVip:是否是Vip
    isSend:物品是否已发出
 */

public class Good {
    private String OrderNumber;
    private String ArriveTime;
    private String Destination;
    private String isVip;
    private String isSend;

    public Good(String OrderNumber, String ArriveTime, String Destination, String isVip, String isSend) {
        this.OrderNumber=OrderNumber;
        this.ArriveTime=ArriveTime;
        this.Destination=Destination;
        this.isSend=isSend;
        this.isVip=isVip;
    }

    public Good(){};

    public String getOrderNumber() {
        return OrderNumber;
    }
    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }
    public String getArriveTime() {
        return ArriveTime;
    }
    public void setArriveTime(String ArriveTime) {
        this.ArriveTime = ArriveTime;
    }
    public String getDestination() {
        return Destination;
    }
    public void setDestination(String Destination) {
        this.Destination =Destination;
    }
    public String getIsSend() {
        return isSend;
    }
    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
    public String getIsVip() {
        return isVip;
    }
    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }
}
