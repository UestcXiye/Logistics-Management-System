package com.example.demo.service;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;

/**
 * 实现数据库操作：修改，删除，插入数据
 * timeNodeUpdate：修改timemap表数据
 * timeNodeDelete：删除timemap表数据
 * timeNodeInsert：插入数据

 * costNodeUpdate：修改costmap表数据
 * costNodeDelete：删除costmap表数据
 * costNodeInsert：插入数据

 * goodsNodeUpdate：修改goodlist表数据
 * goodsNodeDelete：删除goodlist表数据
 * goodsNodeInsert：插入数据
 */

public interface UpdateInfoService {

    public boolean timeNodeUpdate(Time update_time);
    public boolean timeNodeDelete(String CityName);
    public boolean timeNodeInsert(Time insert_time);

    public boolean costNodeUpdate(Cost update_cost);
    public boolean costNodeDelete(String CityName);
    public boolean costNodeInsert(Cost insert_cost);

    public boolean goodsNodeUpdate(Good update_good);
    public boolean goodsNodeInsert(Good insert_good);
    public boolean goodsNodeDelete(String OrderNumber);
}
