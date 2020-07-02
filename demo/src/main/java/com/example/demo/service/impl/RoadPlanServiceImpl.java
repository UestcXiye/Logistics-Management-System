package com.example.demo.service.impl;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.SortedGood;
import com.example.demo.POJO.Time;
import com.example.demo.dao.costDao;
import com.example.demo.dao.sortedGoodsDao;
import com.example.demo.dao.timeDao;
import com.example.demo.service.RoadPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 提供最少时间，最少费用，综合最优，排序以及筛选服务
 * MinTime:最少时间方案
 * MinCost:最少费用方案
 * MinGeneral:综合最优方案
 * Sort:根据到达成都时间排序
 * Select:根据choice进行物品筛选
 */

@Repository
public class RoadPlanServiceImpl implements RoadPlanService {

    @Autowired
    private timeDao timeDao;

    @Autowired
    private costDao costDao;

    @Autowired
    private sortedGoodsDao sortedGoodsDao;

    private int CityNum=8;

    @Override
    public List MinTime() {
        List<Time> timeCost=timeDao.read();
        int [][]cost=new int[timeCost.size()][CityNum];
        for(int i=0;i<timeCost.size();i++){
            for(int j=0;j<CityNum;j++){
                String strCost=timeCost.get(i).getCost(j);
                if(strCost==null){ cost[i][j]=1000; }
                else{ cost[i][j]=Integer.parseInt(strCost); }
            }
        }
        List<SortedGood> goodsList=sortedGoodsDao.dijkstra(cost);
        for(int i=0;i<goodsList.size();i++){
            goodsList.get(i).setCost(goodsList.get(i).getCost()+"小时");
        }
        return goodsList;
    }

    @Override
    public List MinCost() {
        List<Cost> Cost=costDao.read();
        int [][]cost=new int[Cost.size()][CityNum];
        for(int i=0;i<Cost.size();i++){
            for(int j=0;j<CityNum;j++){
                String strCost=Cost.get(i).getCost(j);
                if(strCost==null){ cost[i][j]=1000; }
                else{ cost[i][j]=Integer.parseInt(strCost); }
            }
        }
        List<SortedGood> goodsList=sortedGoodsDao.dijkstra(cost);
        for(int i=0;i<goodsList.size();i++){
            goodsList.get(i).setCost(goodsList.get(i).getCost()+"元");
        }
        return goodsList;
    }

    @Override
    public List MinGeneral() {
        List<Cost> Cost=costDao.read();
        List<Time> Time=timeDao.read();
        int [][]cost=new int[Cost.size()][CityNum];
        for(int i=0;i<Cost.size();i++){
            for(int j=0;j<CityNum;j++){
                String strCost=Cost.get(i).getCost(j);
                String strTime=Time.get(i).getCost(j);
                if(strCost==null){ cost[i][j]=1000; }
                else{ cost[i][j]=Integer.parseInt(strCost)+Integer.parseInt(strTime)/6; }
            }
        }
        List<SortedGood> goodsList=sortedGoodsDao.dijkstra(cost);
        for(int i=0;i<goodsList.size();i++){
            goodsList.get(i).setCost("综合花费："+goodsList.get(i).getCost());
        }
        return goodsList;
    }

    @Override
    public List Sort(List<SortedGood> SortedGood){
        SortedGood=sortedGoodsDao.sort(SortedGood);
        return SortedGood;
    }

    @Override
    public List Select(List<SortedGood> SortedGood,int choice){
        SortedGood=sortedGoodsDao.select(SortedGood,choice);
        return SortedGood;
    }
}
