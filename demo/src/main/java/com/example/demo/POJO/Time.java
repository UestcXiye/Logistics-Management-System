package com.example.demo.POJO;

/**定义Cost实体类，储存costmap表信息。
 *  CityName:物品发出城市

 * 物品发出城市到各个城市的费用：
 * 成都：
 * 重庆：
 * 长沙：
 * …………
 */

public class Time {
    private String CityName;
    private String Chengdu;
    private String Chongqing;
    private String Changsha;
    private String Wuhan;
    private String Guangzhou;
    private String Hangzhou;
    private String Nanjing;
    private String Shanghai;

    public Time(String CityName, String Chengdu, String Chongqing, String Changsha, String Wuhan, String Guangzhou, String Hangzhou, String Nanjing, String Shanghai) {
        this.CityName=CityName;
        this.Chengdu=Chengdu;
        this.Chongqing=Chongqing;
        this.Changsha=Changsha;
        this.Wuhan=Wuhan;
        this.Guangzhou=Guangzhou;
        this.Hangzhou=Hangzhou;
        this.Nanjing=Nanjing;
        this.Shanghai=Shanghai;
    }

    public Time(){};

    public String getCityName() {
        return CityName;
    }
    public void setCityName(String CityName) {
        this.CityName = CityName;
    }
    public String getChongqing() {
        return Chongqing;
    }
    public void setChongqing(String Chongqing) {
        this.Chongqing = Chongqing;
    }
    public String getChengdu() {
        return Chengdu;
    }
    public void setChengdu(String Chengdu) {
        this.Chengdu =Chengdu;
    }
    public String getChangsha() {
        return Changsha;
    }
    public void setChangsha(String Changsha) {
        this.Changsha = Changsha;
    }
    public String getWuhan() {
        return Wuhan;
    }
    public void setWuhan(String Wuhan) {
        this.Wuhan = Wuhan;
    }
    public String getGuangzhou(){ return Guangzhou;}
    public void setGuangzhou(String Guangzhou) {
        this.Guangzhou = Guangzhou;
    }

    public String getHangzhou(){ return Hangzhou;}
    public void setHangzhou(String Hangzhou) {
        this.Hangzhou = Hangzhou;
    }

    public String getNanjing(){ return Nanjing;}
    public void setNanjing(String Nanjing) {
        this.Nanjing = Nanjing;
    }

    public String getShanghai(){ return Shanghai;}
    public void setShanghai(String Shanghai) {
        this.Shanghai = Shanghai;
    }

    public String getCost(int searchNum){
        if(searchNum==0) { return Changsha; }
        else if(searchNum==1) { return Chengdu; }
        else if(searchNum==2) { return Chongqing; }
        else if(searchNum==3) { return Guangzhou; }
        else if(searchNum==4) { return Hangzhou; }
        else if(searchNum==5) { return Nanjing; }
        else if(searchNum==6) { return Shanghai; }
        else { return Wuhan; }
    }

    public boolean setCost(String key,int searchNum){
        if(searchNum==0) { setChangsha(key); }
        else if(searchNum==1) { setChengdu(key); }
        else if(searchNum==2) { setChongqing(key); }
        else if(searchNum==3) { setGuangzhou(key); }
        else if(searchNum==4) { setHangzhou(key); }
        else if(searchNum==5) { setNanjing(key); }
        else if(searchNum==6) { setShanghai(key); }
        else { setWuhan(key); }
        return true;
    }
}
