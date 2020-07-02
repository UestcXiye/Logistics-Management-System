package com.example.demo.service.impl;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import com.example.demo.dao.costDao;
import com.example.demo.dao.goodsDao;
import com.example.demo.dao.timeDao;
import com.example.demo.service.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 数据更新：
 * GoodsList,timeMap和costMap数据删除，替换，增加等
 */

@Repository
public class UpdateServiceImpl implements UpdateInfoService {

    @Autowired
    private costDao CostDao;
    @Autowired
    private timeDao TimeDao;
    @Autowired
    private goodsDao GoodsDao;

    @Override
    public boolean timeNodeUpdate(Time update_time){return TimeDao.update(update_time); }

    @Override
    public boolean timeNodeDelete(String CityName){
        return TimeDao.delete(CityName);
    }

    @Override
    public boolean timeNodeInsert(Time insert_time){
        return TimeDao.insert(insert_time);
    }

    @Override
    public boolean costNodeUpdate(Cost update_cost){
        return CostDao.update(update_cost);
    }

    @Override
    public boolean costNodeDelete(String CityName){
        return CostDao.delete(CityName);
    }

    @Override
    public boolean costNodeInsert(Cost insert_cost){
        return CostDao.insert(insert_cost);
    }

    @Override
    public boolean goodsNodeUpdate(Good update_good){
        return GoodsDao.update(update_good);
    }

    @Override
    public boolean goodsNodeInsert(Good insert_good){
        return GoodsDao.insert(insert_good);
    }

    @Override
    public boolean goodsNodeDelete(String OrderNumber){
        return GoodsDao.delete(OrderNumber);
    }
}
