package com.example.demo.controller;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import com.example.demo.dao.Impl.WordAnalyzeImpl;
import com.example.demo.dao.Impl.SentenceAnalyzeImpl;
import com.example.demo.service.SQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL解析器控制层
 */

@RestController
@RequestMapping("/SQL")
public class SQLController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLService sqlService;

    /**
     * SQL解析器
     * @param SQL
     * @return
     */

    @GetMapping("/SQLAnalyze")
    public List SQLAnalyze(String SQL){
        SQL=SQL.toLowerCase();
        return sqlService.SQLAnalyze(SQL);
    }
}
