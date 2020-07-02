package com.example.demo.dao.Impl;

import com.example.demo.POJO.User;
import com.example.demo.dao.loginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 用户登陆，用户账号密码是否为管理员
 */

@Repository
public class loginDaoImpl implements loginDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean lookUser(User User) {
        String sql="select * from id";
        List<User> Users=jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for(int i=0;i<Users.size();i++){
            if(User.getAccount().equals(Users.get(i).getAccount())&&User.getPassword().equals(Users.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
}
