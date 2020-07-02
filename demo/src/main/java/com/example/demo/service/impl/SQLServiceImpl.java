package com.example.demo.service.impl;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import com.example.demo.POJO.word;
import com.example.demo.dao.Impl.SentenceAnalyzeImpl;
import com.example.demo.dao.Impl.WordAnalyzeImpl;
import com.example.demo.service.SQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SQLServiceImpl implements SQLService {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List SQLAnalyze(String SQL){
        WordAnalyzeImpl wordAnalyze=new WordAnalyzeImpl();
        wordAnalyze.words=new ArrayList<word>();
        wordAnalyze.p=0;
        wordAnalyze.analyze(SQL);
        SentenceAnalyzeImpl sentenceAnalyze=new SentenceAnalyzeImpl();
        sentenceAnalyze.p=0;
        boolean success=sentenceAnalyze.checkSentence();
        List<String> StrList=new ArrayList<>();
        if(success==true&&SQL.substring(0,6).equals("select")){
            logger.info("SQL解析器执行SELECT语句");
            String []sqlWords=SQL.split(" ");
            for(int i=3;i<sqlWords.length;i++){
                if(sqlWords[i].equals("goodslist")){
                    return jdbcTemplate.query(SQL,new BeanPropertyRowMapper<Good>(Good.class));
                }
                if(sqlWords[i].equals("timemap")){
                    return jdbcTemplate.query(SQL,new BeanPropertyRowMapper<Time>(Time.class));
                }else{
                    return jdbcTemplate.query(SQL,new BeanPropertyRowMapper<Cost>(Cost.class));
                }
            }
            StrList.add("");
            return StrList;
        }
        else if(success==true){
            StrList.add("true");
            logger.info("SQL解析器执行其它语句");
            jdbcTemplate.execute(SQL);
            return StrList;
        }else{
            StrList.add("");
            logger.info("SQL语句不正确或者执行结果不正确");
            return StrList;
        }
    }
}
