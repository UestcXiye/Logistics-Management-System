package com.example.demo.dao.Impl;

import com.example.demo.dao.SentenceAnalyzeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SentenceAnalyzeImpl implements SentenceAnalyzeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;  //加载jdbc驱动

    /**
     * SQL语法分析，对应Sentence第一个关键字字段选取不同操作
     * @param Sentence
     * @return
     */
    public static ArrayList<String> list=new ArrayList<>();
    private List<String> Id= Arrays.asList("id","goodslist","timemap","costmap","processing","stu","ordernumber","arrivetime","destination","isvip","issend","cityname","changsha","chengdu","chongqing","guangzhou","hangzhou","nanjing","shanghai","wuhan","account","password");
    public int p=0;

    @Override
    public boolean checkSentence(){
        if(match("select")){
            if(match("\\*")&&match("from")&&id()){
                if(match("where")&&id()&&match("=")&&match("'")&&match("[0-9a-z]+$")&&match("'")){
                    return true;
                }
                if(p>=WordAnalyzeImpl.words.size()){ return true;}
            }else if(idList()&&match("from")&&id()){
                String[]column=new String[list.size()];
                for(int i=0;i<list.size();i++){
                    column[i]=list.get(i);
                }
                if(p>=WordAnalyzeImpl.words.size()){ return true;}
            }
        }else if(match("insert" )&&match("into")&&id()&&match("values")&&match("\\(")&&valueList()&&match("\\)")){
            if(p>=WordAnalyzeImpl.words.size()){ return true;}
        }else if(match("creat")&&match("table")&&id()){
            if(p>=WordAnalyzeImpl.words.size()){ return true;}
        }else if(match("update")&&id()&&match("set")&&setList()&&match("where")&&id()&&match("=")&&match("'")&&match("[0-9a-z]+$")&&match("'")){
            if(p>=WordAnalyzeImpl.words.size()){ return true;}
        }else if(match("delete")&&match("from")&&id()&&match("where")&&id()&&match("=")&&match("'")&&match("[0-9a-z]+$")&&match("'")){
            if(p>=WordAnalyzeImpl.words.size()){ return true;}
        }
        return false;
    }

    boolean match(String regex){
        if(p>= WordAnalyzeImpl.words.size()){ return false; }
        String token=WordAnalyzeImpl.words.get(p).name;
        if(token.matches(regex)){
            p++;
            return true;
        }
        else{
            return false;
        }
    }


    boolean idList(){
        list=new ArrayList<String>();
        if(id()){
            list.add(WordAnalyzeImpl.words.get(p-1).name);
            while(match(",")&&id()){
                list.add(WordAnalyzeImpl.words.get(p-1).name);
            }
            return true;
        }else{
            return false;
        }
    }

    boolean setList(){
        list= new ArrayList<>();
        if(id()){
            list.add(WordAnalyzeImpl.words.get(p-1).name);
            if((match("=")&&match("'")&&match("[a-z0-9]+$")&&match("'"))) {
                list.add(WordAnalyzeImpl.words.get(p - 3).name + WordAnalyzeImpl.words.get(p - 2).name + WordAnalyzeImpl.words.get(p - 1).name);
            }else{
                return false;
            }
            while(match(",")&&id()){
                list.add(WordAnalyzeImpl.words.get(p-1).name);
                if(match("=")&&match("'")&&match("[a-z0-9]+$")&&match("'")){
                    list.add(WordAnalyzeImpl.words.get(p - 3).name + WordAnalyzeImpl.words.get(p - 2).name + WordAnalyzeImpl.words.get(p - 1).name);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    boolean valueList(){
        list= new ArrayList<>();
        if(match("'")&&match("[a-z0-9]+$")&&match("'")){
            list.add(WordAnalyzeImpl.words.get(p-3).name+WordAnalyzeImpl.words.get(p-2).name+WordAnalyzeImpl.words.get(p-1).name);
            while(match(",")&&match("'")&&match("[0-9a-z]+$")&&match("'")){
                list.add(WordAnalyzeImpl.words.get(p-3).name+WordAnalyzeImpl.words.get(p-2).name+WordAnalyzeImpl.words.get(p-1).name);
            }
            return true;
        }else{
            return false;
        }
    }

    boolean id(){
        if(p>=WordAnalyzeImpl.words.size()){ return false; }
        String token=WordAnalyzeImpl.words.get(p).name;
        for(int i=0;i<Id.size();i++){
            if(token.equals(Id.get(i))){
                p++;
                return true;
            }
        }
        return false;
    }
}
