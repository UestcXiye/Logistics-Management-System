package com.example.demo.dao;

import com.example.demo.POJO.Cost;

import java.util.List;

public interface costDao {
    public List read(); //读出costmap表中的所有内容
    public Cost search(String key); //根据城市查询节点信息
    public boolean update(Cost cost); //修改costmap表中的内容
    public boolean delete(String key);  //删除costmap表中的内容
    public boolean insert(Cost cost);  //插入节点内容
}
