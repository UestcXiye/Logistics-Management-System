package com.example.demo.dao.Impl;

import com.example.demo.dao.tableOperateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 实现数据库表操作
 * CreatTable:建立表
 * DeleteTable:删除表
 */

@Repository
public class TableOperateImpl implements tableOperateDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean CreatTable(String sql){
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public boolean DeleteTable(String sql){
        jdbcTemplate.execute(sql);
        return true;
    }
}
