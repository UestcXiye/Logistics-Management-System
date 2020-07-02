package com.example.demo.service.impl;

import com.example.demo.POJO.Good;
import com.example.demo.POJO.Processing;
import com.example.demo.dao.goodsDao;
import com.example.demo.service.DeliveryStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 货物状态查看和发货
 * delivery_state:状态查看
 * SendOut:发货处理
 */

@Repository
public class DeliveryStateServiceImpl implements DeliveryStateService {

    @Autowired
    private goodsDao goodsDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String delivery_state(String OrderNumber){
        String delivery_state_str;
        String arrive_time;
        List<Good> goodsList=goodsDao.read();
        Good search_good=null;
        for(int i=0;i<goodsList.size();i++){
            if(OrderNumber.equals(goodsList.get(i).getOrderNumber())){{
                search_good=goodsList.get(i);
                break;
            }}
        }
        if(search_good==null){
            return "";
        }
        arrive_time=search_good.getArriveTime().substring(4,10);
        if(arrive_time.substring(0,1).compareTo("0")==0){
            arrive_time=arrive_time.replaceFirst("0"," ");
        }
        if(arrive_time.substring(2,3).compareTo("0")==0){
            arrive_time=arrive_time.replaceFirst("0"," ");
        }
        if(arrive_time.substring(4,5).compareTo("0")==0){
            arrive_time=arrive_time.replaceFirst("0"," ");
        }
        if(search_good.getIsSend().equals("否")){
            delivery_state_str="物品未发出，"+"预计到达时间:"+arrive_time.substring(0,2)+"月"+arrive_time.substring(2,4)+"日"+arrive_time.substring(4,6)+"点";
        }else{
            delivery_state_str="物品已发出，"+"到达时间: "+arrive_time.substring(0,2)+"月"+arrive_time.substring(2,4)+"日"+arrive_time.substring(4,6)+"点";
        }
        return delivery_state_str;
    }

    @Override
    public boolean SendOut(String OrderNumber){
        String sql="select * from goodslist where OrderNumber='"+OrderNumber+"'";
        List<Good>goodsList=goodsDao.read();
        for(int i=0;i<goodsList.size();i++){
            if(goodsList.get(i).getOrderNumber().equals(OrderNumber)){
                if(goodsList.get(i).getIsSend().equals("true")){
                    return false;
                }
                break;
            }
        }
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getGoodWriting()==1||processing.getGoodReading()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set GoodWriting=1");
            Good good = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Good>(Good.class));
            jdbcTemplate.execute("update processing set GoodWriting=0");
            if (good.getIsSend().equals("true")) {
                return false;
            } else {
                good.setIsSend("true");
                return goodsDao.update(good);
            }
        }
    }
}
