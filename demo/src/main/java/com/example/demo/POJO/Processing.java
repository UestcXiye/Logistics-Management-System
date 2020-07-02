package com.example.demo.POJO;

/**
 * 进程管理，管理表是否存在读或写操作
 */

public class Processing {
    private int GoodWriting;
    private int CostWriting;
    private int TimeWriting;
    private int GoodReading;
    private int CostReading;
    private int TimeReading;

    public int getGoodWriting() {
        return GoodWriting;
    }

    public void setGoodWriting(int goodWriting) {
        GoodWriting = goodWriting;
    }

    public int getCostWriting() {
        return CostWriting;
    }

    public void setCostWriting(int costWriting) {
        CostWriting = costWriting;
    }

    public int getTimeWriting() {
        return TimeWriting;
    }

    public void setTimeWriting(int timeWriting) {
        TimeWriting = timeWriting;
    }

    public int getGoodReading() {
        return GoodReading;
    }

    public void setGoodReading(int goodReading) {
        GoodReading = goodReading;
    }

    public int getCostReading() {
        return CostReading;
    }

    public void setCostReading(int costReading) {
        CostReading = costReading;
    }

    public int getTimeReading() {
        return TimeReading;
    }

    public void setTimeReading(int timeReading) {
        TimeReading = timeReading;
    }
}
