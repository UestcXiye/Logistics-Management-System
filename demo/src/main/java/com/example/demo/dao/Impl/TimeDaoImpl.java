package com.example.demo.dao.Impl;

import com.example.demo.POJO.Processing;
import com.example.demo.POJO.Time;
import com.example.demo.dao.timeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

//通过jdbc实现mysql数据库timeMap表的基本操作

@Repository
public class TimeDaoImpl implements timeDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查找TimeMap表所有数据，返回List<Time>
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @return
     */

    @Override
    public List read() { //读取数据表，并将数据表中的数据存入
        List<Time> time_list = new ArrayList<>();
        String sql = "select * from timemap";
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getCostWriting()==1){ return time_list; }
        else {
            jdbcTemplate.execute("update processing set TimeReading=1");
            time_list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Time>(Time.class));
            jdbcTemplate.execute("update processing set TimeReading=0");
            return time_list;
        }
    }

    /**
     * 根据CityName查找表数据，返回Time实体类
     * processing实现进程管理，用户读取表数据需要判断此表是否存在写操作
     * @param key
     * @return
     */

    @Override
    public Time search(String key){
        String sql="select * from timemap where CityName=?";
        List<Time> TimeList=read();
        Time time=new Time();
        int i;
        for(i=0;i<TimeList.size();i++){
            if(TimeList.get(i).getCityName().equals(key)){
                break;
            }
        }
        if(i==TimeList.size()){
            return time;
        }
        Processing processing=jdbcTemplate.queryForObject("select * from processing",new BeanPropertyRowMapper<Processing>(Processing.class));
        if(processing.getCostWriting()==1){ return time; }
        else {
            jdbcTemplate.execute("update processing set TimeReading=1");
            Time search_time = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Time>(Time.class), key);
            jdbcTemplate.execute("update processing set TimeReading=0");
            return search_time;
        }
    }

    /**
     * 插入time数据，返回boolean
     * processing实现进程管理，用户插入表数据需要判断此表是否存在读或者写操作
     * @param time
     * @return
     */

    @Override
    public boolean insert(Time time) {
        Time searchTime = search(time.getCityName());
        if (searchTime.getCityName() != null) {
            return false;
        }
        String sql = "insert into timemap(CityName,Changsha,Chengdu,Chongqing,Guangzhou,Hangzhou,Nanjing,Shanghai,Wuhan) values(?,?,?,?,?,?,?,?,?)";
        Processing processing = jdbcTemplate.queryForObject("select * from processing", new BeanPropertyRowMapper<Processing>(Processing.class));
        if (processing.getCostWriting() == 1) {
            return false;
        } else {
            jdbcTemplate.execute("update processing set TimeWriting=1");
            boolean success=jdbcTemplate.update(sql, time.getCityName(), time.getChangsha(), time.getChengdu(), time.getChongqing(), time.getGuangzhou(), time.getHangzhou(), time.getNanjing(), time.getShanghai(), time.getWuhan()) > 0;
            jdbcTemplate.execute("update processing set TimeWriting=0");
            return success;
        }
    }

    /**
     * 更新timeMap表数据，返回boolean
     * processing实现进程管理，用户更新表数据需要判断此表是否存在读或写操作
     * @param update_time
     * @return
     */

    @Override
    public boolean update(Time update_time) {
        Time searchTime = search(update_time.getCityName());
        if (searchTime.getCityName() == null) {
            return false;
        }
        String sql = "update timemap set Chengdu=?,Chongqing=?,Changsha=?,Wuhan=?,Guangzhou=?,Hangzhou=?,Nanjing=?,Shanghai=? where CityName='" + update_time.getCityName() + "'";
        Processing processing = jdbcTemplate.queryForObject("select * from processing", new BeanPropertyRowMapper<Processing>(Processing.class));
        if (processing.getCostWriting() == 1) {
            return false;
        } else {
            jdbcTemplate.execute("update processing set TimeWriting=1");
            boolean success = jdbcTemplate.update(sql, update_time.getChengdu(), update_time.getChongqing(), update_time.getChangsha(), update_time.getWuhan(), update_time.getGuangzhou(), update_time.getHangzhou(), update_time.getNanjing(), update_time.getShanghai()) > 0;
            jdbcTemplate.execute("update processing set TimeWriting=0");
            return success;
        }
    }

    /**
     * 删除timeMap表数据，返回boolean
     * processing实现进程管理，用户删除表数据需要判断此表是否存在读或写操作
     * @param key
     * @return
     */

    @Override
    public boolean delete(String key){
        Time searchTime=search(key);
        if(searchTime.getCityName()==null){
            return false;
        }
        String sql="delete from timemap where CityName=?";
        Processing processing = jdbcTemplate.queryForObject("select * from processing", new BeanPropertyRowMapper<Processing>(Processing.class));
        if (processing.getCostWriting() == 1) {
            return false;
        } else {
            jdbcTemplate.execute("update processing set TimeWriting=1");
            boolean success=jdbcTemplate.update(sql, key) > 0;
            jdbcTemplate.execute("update processing set TimeWriting=0");
            return success;
        }
    }
}
