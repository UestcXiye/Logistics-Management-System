package com.example.demo.service.impl;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import com.example.demo.dao.goodsDao;
import com.example.demo.dao.costDao;
import com.example.demo.dao.timeDao;
import com.example.demo.service.ShowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据展示服务，实现数据获取及处理
 */

@Service
public class ShowInfoServiceImpl implements ShowInfoService {

    @Autowired
    private timeDao timeDao;
    @Autowired
    private goodsDao goodsDao;
    @Autowired
    private costDao costDao;

    /**
     * 查询所有物品信息，展示所有goodsList数据
     * @return
     */

    @Override
    public List GoodsInfo_print(){
        List<Good> goods = goodsDao.read();
        return goods;
    }

    /**
     * 根据订单号查询物品信息
     * @param OrderNumber
     * @return
     */

    @Override
    public Good Goods_search(String OrderNumber){
        List<Good> goodslist=goodsDao.read();
        Good search_good=new Good();
        for(int i=0;i<goodslist.size();i++){
            if(goodslist.get(i).getOrderNumber().equals(OrderNumber)){
                search_good=goodslist.get(i);
            }
        }
        return search_good;
    }

    /**
     * 根据城市节点查询该节点物流信息
     * @param CityName
     * @return
     */

    @Override
    public Time Nodes_search(String CityName){
        Time city_time=new Time();
        List<Time> timeList=timeDao.read();
        int i;
        for(i=0;i<timeList.size();i++){
            if(timeList.get(i).getCityName().equals(CityName)){
                city_time=timeList.get(i);
                break;
            }
        }
        if(i==timeList.size()){
            return city_time;
        }
        Cost city_cost=costDao.search(CityName);
        if(city_time.getChengdu()==null){
            city_time.setChengdu("物流路线未开通");
        }else {
            city_time.setChengdu("时间:" + city_time.getChengdu() + "小时 费用:" + city_cost.getChengdu() + "元");
        }
        if(city_time.getChongqing()==null){
            city_time.setChongqing("物流路线未开通");
        } else {
            city_time.setChongqing("时间:" + city_time.getChongqing() + "小时 费用:" + city_cost.getChongqing() + "元");
        }
        if(city_time.getChangsha()==null){
            city_time.setChangsha("物流路线未开通");
        } else {
            city_time.setChangsha("时间:" + city_time.getChangsha() + "小时 费用:" + city_cost.getChangsha() + "元");
        }
        if(city_time.getWuhan()==null){
            city_time.setWuhan("物流路线未开通");
        } else {
            city_time.setWuhan("时间:" + city_time.getWuhan() + "小时 费用:" + city_cost.getWuhan() + "元");
        }
        if(city_time.getGuangzhou()==null){
            city_time.setGuangzhou("物流路线未开通");
        } else{
            city_time.setGuangzhou("时间:" + city_time.getGuangzhou() + "小时 费用:" + city_cost.getGuangzhou() + "元");
        }
        if(city_time.getHangzhou()==null){
            city_time.setHangzhou("物流路线未开通");
        } else {
            city_time.setHangzhou("时间:" + city_time.getHangzhou() + "小时 费用:" + city_cost.getHangzhou() + "元");
        }
        if(city_time.getNanjing()==null){
            city_time.setNanjing("物流路线未开通");
        } else {
            city_time.setNanjing("时间:" + city_time.getNanjing() + "小时 费用:" + city_cost.getNanjing() + "元");
        }
        if(city_time.getShanghai()==null){
            city_time.setShanghai("物流路线未开通");
        } else {
            city_time.setShanghai("时间:"+city_time.getShanghai()+"小时 费用:"+city_cost.getShanghai()+"元");
        }
        return city_time;
    }

    /**
     * 展示所有节点的信息
     * @return
     */

    @Override
    public List Nodes_print(){
        List<Time> time = timeDao.read();
        List<Cost> cost = costDao.read();
        for(int i=0;i<time.size();i++){
            Time city_time=time.get(i);
            Cost city_cost=cost.get(i);
            if(city_time.getChengdu()==null){
                time.get(i).setChengdu("物流路线未开通");
            }else {
                time.get(i).setChengdu("时间:" + city_time.getChengdu() + "小时 费用:" + city_cost.getChengdu() + "元");
            }
            if(city_time.getChongqing()==null){
                time.get(i).setChongqing("物流路线未开通");
            } else {
                time.get(i).setChongqing("时间:" + city_time.getChongqing() + "小时 费用:" + city_cost.getChongqing() + "元");
            }
            if(city_time.getChangsha()==null){
                time.get(i).setChangsha("物流路线未开通");
            } else {
                time.get(i).setChangsha("时间:" + city_time.getChangsha() + "小时 费用:" + city_cost.getChangsha() + "元");
            }
            if(city_time.getWuhan()==null){
                time.get(i).setWuhan("物流路线未开通");
            } else {
                time.get(i).setWuhan("时间:" + city_time.getWuhan() + "小时 费用:" + city_cost.getWuhan() + "元");
            }
            if(city_time.getGuangzhou()==null){
                time.get(i).setGuangzhou("物流路线未开通");
            } else{
                time.get(i).setGuangzhou("时间:" + city_time.getGuangzhou() + "小时 费用:" + city_cost.getGuangzhou() + "元");
            }
            if(city_time.getHangzhou()==null){
                time.get(i).setHangzhou("物流路线未开通");
            } else {
                time.get(i).setHangzhou("时间:" + city_time.getHangzhou() + "小时 费用:" + city_cost.getHangzhou() + "元");
            }
            if(city_time.getNanjing()==null){
                time.get(i).setNanjing("物流路线未开通");
            } else {
                time.get(i).setNanjing("时间:" + city_time.getNanjing() + "小时 费用:" + city_cost.getNanjing() + "元");
            }
            if(city_time.getShanghai()==null){
                time.get(i).setShanghai("物流路线未开通");
            } else {
                time.get(i).setShanghai("时间:"+city_time.getShanghai()+"小时 费用:"+city_cost.getShanghai()+"元");
            }
        }
        return time;
    }
}
