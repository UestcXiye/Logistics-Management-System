package com.example.demo.dao;

import com.example.demo.POJO.Time;

import java.util.List;

public interface timeDao {
    public List read();  //读取timemap所有内容
    public Time search(String key);  //根据城市名查询节点信息
    public boolean update(Time update_time);  //修改，更新数据
    public boolean delete(String key);  //删除数据
    public boolean insert(Time time);  //插入数据
}
