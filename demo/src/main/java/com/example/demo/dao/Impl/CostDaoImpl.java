package com.example.demo.dao.Impl;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Processing;
import com.example.demo.dao.costDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

//通过jdbc实现mysql数据库CostMap基本操作

@Repository
public class CostDaoImpl implements costDao {
    //加载jdbc驱动
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 读取CostMap表所有数据，返回list<Cost>
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @param
     * @return
     */

    @Override
    public List read() { //读取数据表，并将数据表中的数据存入
        List<Cost> cost = new ArrayList<>();
        String sql = "select * from costmap";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getCostWriting()==1){ return cost; }
        else{
            jdbcTemplate.execute("update processing set CostReading=1");
            cost = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cost>(Cost.class));
            jdbcTemplate.execute("update processing set CostReading=0");
        }
        return cost;
    }

    /**
     * 根据订单号读取CostMap表数据，返回实体类Cost
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @param key
     * @return
     */

    @Override
    public Cost search(String key){
        List<Cost> CostList=read();
        Cost cost=new Cost();
        int i;
        for(i=0;i<CostList.size();i++){
            if(CostList.get(i).getCityName().equals(key)){
                break;
            }
        }
        if(i==CostList.size()){
            return cost;
        }
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        String sql="select * from costmap where CityName=?";
        if(processing.getCostWriting()==1){ return cost; }
        else {
            jdbcTemplate.execute("update processing set CostReading=1");
            cost = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Cost>(Cost.class), key);
            jdbcTemplate.execute("update processing set CostReading=0");
        }
        return cost;
    }

    /**
     * 更新CostMap表数据，返回boolean
     * processing实现进程管理，用户更新表数据需要判断此表是否存在读或写操作
     * @param cost
     * @return
     */

    @Override
    public boolean update(Cost cost){
        Cost searchCost=search(cost.getCityName());
        if(searchCost.getCityName()==null){
            return false;
        }
        String sql="update costmap set Chengdu=?,Chongqing=?,Changsha=?,Wuhan=?,Guangzhou=?,Hangzhou=?,Nanjing=?,Shanghai=? where CityName='"+cost.getCityName()+"'";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getCostWriting()==1||processing.getCostReading()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set CostWriting=1");
            boolean success=jdbcTemplate.update(sql, cost.getChengdu(), cost.getChongqing(), cost.getChangsha(), cost.getWuhan(), cost.getGuangzhou(), cost.getHangzhou(), cost.getNanjing(), cost.getShanghai()) > 0;
            jdbcTemplate.execute("update processing set CostWriting=0");
            return success;
        }
    }

    /**
     * 查找CostMap表数据，返回boolean
     * processing实现进程管理，用户插入表数据需要判断此表是否存在读或写操作
     * @param cost
     * @return
     */

    @Override
    public boolean insert(Cost cost){
        Cost searchCost=search(cost.getCityName());
        if(searchCost.getCityName()!=null){
            return false;
        }
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        String sql="insert into costmap(CityName,Chengdu,Chongqing,Changsha,Wuhan,Guangzhou,Hangzhou,Nanjing,Shanghai) values(?,?,?,?,?,?,?,?,?)";
        if(processing.getCostReading()==1||processing.getCostWriting()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set CostWriting=1");
            boolean success = jdbcTemplate.update(sql, cost.getCityName(), cost.getChengdu(), cost.getChongqing(), cost.getChangsha(), cost.getWuhan(), cost.getGuangzhou(), cost.getHangzhou(), cost.getNanjing(), cost.getShanghai()) > 0;
            jdbcTemplate.execute("update processing set CostWriting=0");
            return success;
        }
    }

    /**
     * 删除CostMap表数据，返回boolean
     * processing实现进程管理，用户删除表数据需要判断此表是否存在读或写操作
     * @param key
     * @return
     */

    @Override
    public boolean delete(String key){
        Cost searchCost=search(key);
        if(searchCost.getCityName()==null){
            return false;
        }
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        String sql="delete from costmap where CityName=?";
        if(processing.getCostWriting()==1||processing.getCostReading()==1){ return false; }
        else {
            jdbcTemplate.execute("update processing set CostWriting=1");
            boolean success=jdbcTemplate.update(sql, key) > 0;
            jdbcTemplate.execute("update processing set CostWriting=0");
            return success;
        }
    }
}
