package com.example.demo.dao;

import com.example.demo.POJO.SortedGood;
import java.util.List;

/**
 * 实现物品的底层算法：
 * sort:根据到达时间和是否是VIP进行排序
 * select:根据是否是VIP和是否已送出进行选择
 * dijkstra:最优路径计算
 */

public interface sortedGoodsDao {
    public List sort(List<SortedGood>goodsList);
    public List select(List<SortedGood>goodList,int choice);
    public List dijkstra(int [][]cost);
}
