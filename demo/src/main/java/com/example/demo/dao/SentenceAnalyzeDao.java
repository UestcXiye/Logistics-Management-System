package com.example.demo.dao;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;

import java.util.List;

/**
 *SQL语法解析器服务
 * sqlAnalyze:SQL语法解析
 * selectAll:对应SQL语法中select语句，读取数据库对应表的所有数据
 * selectGood:查询数据库goodslist表的某一项数据
 * selectTimeMap:查询数据库timemap表的某一项数据
 * selectCostMap:查询数据库costmap表的某一项数据
 */

public interface SentenceAnalyzeDao {
    public boolean checkSentence();
}
