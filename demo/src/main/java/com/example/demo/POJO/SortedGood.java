package com.example.demo.POJO;

/**
 * SortedGood用来存储排序，路径计算后的物品信息
 * OrderNumber：订单号
 * ArriveTime：到达成都时间
 * Destination：目的地
 * isVip：是否是VIP
 * isSend：是否已送出
 * road：物流路线
 * Cost：物流费用
 */

public class SortedGood {
    private String OrderNumber;
    private String ArriveTime;
    private String Destination;
    private String IsVip;
    private String IsSend;
    private String Road;
    private String Cost;

    public SortedGood(String OrderNumber, String ArriveTime, String Destination, String IsVip, String IsSend, String Road, String Cost) {
        this.OrderNumber=OrderNumber;
        this.ArriveTime=ArriveTime;
        this.Destination=Destination;
        this.IsSend=IsSend;
        this.IsVip=IsVip;
        this.Road=Road;
        this.Cost=Cost;
    }

    public SortedGood(Good good){
        this.OrderNumber=good.getOrderNumber();
        this.ArriveTime=good.getArriveTime();
        this.Destination=good.getDestination();
        this.IsSend=good.getIsSend();
        this.IsVip=good.getIsVip();
    }

    public SortedGood(){};

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
        return IsSend;
    }
    public void setIsSend(String isSend) {
        this.IsSend = isSend;
    }
    public String getIsVip() {
        return IsVip;
    }
    public void setIsVip(String isVip) {
        this.IsVip = isVip;
    }
    public String getRoad(){ return Road; }
    public void setRoad(String road){ this.Road=road; }
    public String getCost(){ return Cost; }
    public void setCost(String Cost){ this.Cost=Cost; }
}
