package com.example.demo.dao;

import java.sql.SQLException;

public interface tableOperateDao {
    public boolean CreatTable(String sql);  //创建新的mysql数据库表
    public boolean DeleteTable(String sql);  //删除mysql数据库表
}
