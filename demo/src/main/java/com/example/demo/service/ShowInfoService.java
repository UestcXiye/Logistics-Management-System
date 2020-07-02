package com.example.demo.service;

import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import java.util.List;

/**
 * GoodsInfo_print： 查询所有的物品信息，存储在list中
 * Nodes_print: 查询所有的物流节点信息，存储在list中
 * Goods_search: 根据订单号查询物品信息
 * Nodes_search: 根据发出城市名字查询该城市到其他城市的路径费用
 */

public interface ShowInfoService {
    public List GoodsInfo_print();
    public List Nodes_print();
    public Good Goods_search(String OrderNumber);
    public Time Nodes_search(String CityName);
}
