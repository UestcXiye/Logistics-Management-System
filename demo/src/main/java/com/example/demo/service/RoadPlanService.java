package com.example.demo.service;

import com.example.demo.POJO.SortedGood;

import java.util.List;

/**
 * 提供最少时间，最少费用，综合最优，排序以及筛选服务
 * MinTime:最少时间方案
 * MinCost:最少费用方案
 * MinGeneral:综合最优方案
 * Sort:根据到达成都时间排序
 * Select:根据choice进行物品筛选
 */

public interface RoadPlanService {
    public List MinTime();
    public List MinCost();
    public List MinGeneral();
    public List Sort(List<SortedGood> sortedGoods);
    public List Select(List<SortedGood> sortedGoods,int choice);
}
