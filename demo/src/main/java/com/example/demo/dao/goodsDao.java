package com.example.demo.dao;

import com.example.demo.POJO.Good;

import java.util.List;

public interface goodsDao {
    public List read();  //读取所有的物品信息
    public Good search(String key);  //根据订单号查询该物品信息
    public boolean insert(Good good);  //插入数据
    public boolean update(Good good);  //修改数据
    public boolean delete(String key);  //删除数据
}
