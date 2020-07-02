package com.example.demo.dao.Impl;

import com.example.demo.POJO.Good;
import com.example.demo.POJO.Processing;
import com.example.demo.dao.goodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;

//通过jdbc实现mysql数据库goodsList表基本操作

@Repository
public class GoodsDaoImpl implements goodsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 读取goodsList表所有数据
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @return
     */

    @Override
    public List read() { //读取数据表，并将数据表中的数据存入goods中
        List<Good> goods = new ArrayList<>();
        String sql = "select * from goodslist order by ArriveTime";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getGoodWriting()==1){ return goods; }
        else {
            jdbcTemplate.execute("update processing set GoodReading=1");
            goods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Good>(Good.class));
            jdbcTemplate.execute("update processing set GoodReading=0");
        }

        String[] destination_ch = new String[]{"长沙", "成都", "重庆", "广州", "杭州", "南京", "上海", "武汉"};
        String[] destination_en = new String[]{"Changsha", "Chengdu", "Chongqing", "Guangzhou", "Hangzhou", "Nanjing", "Shanghai", "Wuhan"};
        for (int i = 0; i < goods.size(); i++) {
            for (int j = 0; j < destination_en.length; j++) {
                if (destination_en[j].equals(goods.get(i).getDestination())) {
                    goods.get(i).setDestination(destination_ch[j]);
                    break;
                }
            }
            if (goods.get(i).getIsVip().equals("true")) {
                goods.get(i).setIsVip("是");
            } else {
                goods.get(i).setIsVip("否");
            }
            if (goods.get(i).getIsSend().equals("true")) {
                goods.get(i).setIsSend("是");
            } else {
                goods.get(i).setIsSend("否");
            }
        }
        return goods;
    }

    /**
     * 根据订单号读取goodsList表对应数据
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @return
     */

    @Override
    public Good search(String key){
        Good good=new Good();
        List<Good> GoodsList=read();
        int i;
        for(i=0;i<GoodsList.size();i++){
            if(GoodsList.get(i).getOrderNumber().equals(key)){
                break;
            }
        }
        if(i==GoodsList.size()){
            return good;
        }
        String sql="select * from goodslist where OrderNumber=?";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getGoodWriting()==1){ return good; }
        else {
            jdbcTemplate.execute("update processing set GoodReading=1");
            good = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Good>(Good.class), key);
            jdbcTemplate.execute("update processing set GoodReading=0");
        }
        String[] destination_ch = new String[]{"长沙", "成都", "重庆", "广州", "杭州", "南京", "上海", "武汉"};
        String[] destination_en = new String[]{"Changsha", "Chengdu", "Chongqing", "Guangzhou", "Hangzhou", "Nanjing", "Shanghai", "Wuhan"};
        for(i=0;i<destination_en.length;i++){
            if(good.getDestination().equals(destination_en[i])){
                good.setDestination(destination_ch[i]);
            }
        }
        if (good.getIsVip().equals("true")) {
            good.setIsVip("是");
        } else {
            good.setIsVip("否");
        }
        if (good.getIsSend().equals("true")) {
            good.setIsSend("是");
        } else {
            good.setIsSend("否");
        }
        return good;
    }

    /**
     * 插入数据到goodsList表中
     * processing实现进程管理，用户插入数据需要判断此表是否存在读或写操作
     * @param good
     * @return
     */

    @Override
    public boolean insert(Good good){
        Good searchGood=search(good.getOrderNumber());
        if(searchGood.getDestination()!=null){
            return false;
        }
        String sql="insert into goodslist(OrderNumber,ArriveTime,Destination,isVip,isSend) values(?,?,'"+good.getDestination()+"','"+good.getIsVip()+"','"+good.getIsSend()+"')";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getGoodWriting()==1||processing.getGoodReading()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set GoodWriting=1");
            boolean success=jdbcTemplate.update(sql, good.getOrderNumber(), good.getArriveTime()) > 0;
            jdbcTemplate.execute("update processing set GoodWriting=0");
            return success;
        }
    }

    /**
     * 更新goodList表的数据
     * processing实现进程管理，用户更新数据需要判断此表是否存在读或写操作
     * @param good
     * @return
     */

    @Override
    public boolean update(Good good) {
        Good searchGood = search(good.getOrderNumber());
        if (searchGood.getDestination() == null) {
            return false;
        }
        Processing processing = jdbcTemplate.queryForObject("select * from processing", new BeanPropertyRowMapper<Processing>(Processing.class));
        String sql = "update goodslist set ArriveTime=?,Destination='" + good.getDestination() + "',isVip=?,isSend=? where OrderNumber=" + good.getOrderNumber();
        if (processing.getGoodWriting() == 1 || processing.getGoodReading() == 1) {
            return false;
        } else {
            jdbcTemplate.execute("update processing set GoodWriting=1");
            boolean success = jdbcTemplate.update(sql, good.getArriveTime(), good.getIsVip(), good.getIsSend()) > 0;
            jdbcTemplate.execute("update processing set GoodWriting=0");
            return success;
        }
    }

    /**
     * 删除goodsList表的数据
     * processing实现进程管理，用户删除数据需要判断此表是否存在读或写操作
     * @param key
     * @return
     */

    @Override
    public boolean delete(String key){
        Good searchGood=search(key);
        if(searchGood.getDestination()==null){
            return false;
        }
        String sql="delete from goodslist where OrderNumber=?";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getGoodWriting()==1||processing.getGoodReading()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set GoodWriting=1");
            boolean success = jdbcTemplate.update(sql, key) > 0;
            jdbcTemplate.execute("update processing set GoodWriting=0");
            return success;
        }
    }
}
