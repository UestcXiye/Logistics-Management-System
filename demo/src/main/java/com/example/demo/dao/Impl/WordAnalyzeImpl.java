package com.example.demo.dao.Impl;

import com.example.demo.POJO.word;
import com.example.demo.dao.WordAnalyzeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class WordAnalyzeImpl implements WordAnalyzeDao {

    Logger logger= LoggerFactory.getLogger(getClass());

    private List<String> keyWord= Arrays.asList("select","from","where","insert","order","by","update","set","delete","create","drop","table","values","and","or");
    private List<String> DataType= Arrays.asList("int","varchar","long");
    private List<String> Id= Arrays.asList("id","goodslist","timemap","costmap","processing");

    public static ArrayList<word> words=new ArrayList<>();
    public static int p=0;

    public void analyze(String sentence){
        String token="";
        while(p<sentence.length()){
            char ch=sentence.charAt(p);
            if(ch==' '){  //判断字符是否为空格
                p=p+1;
                continue;
            }
            if(isLetter(ch)){  //判断字符是否为字母
                while(isLetter(ch)||isDigit(ch)){  //字符拼接成单词
                    token+=ch;
                    p=p+1;
                    if(p>=sentence.length()){ break; }
                    ch=sentence.charAt(p);
                }
                if(isKeyWord(token)){  //判断是否是关键词
                    insertKeyWords(token);
                }else if(isDataType(token)){  //是否是数据类型
                    insertDataType(token);
                }else{
                    insertId(token);
                }
                p--;
                token="";
            }else if(isDigit(ch)){  //判断字符是否为数字
                while(isDigit(ch)){  //字符拼接
                    token+=ch;
                    p=p+1;
                    if(p>=sentence.length()){ break; }
                    ch=sentence.charAt(p);
                }
                p--;
                insertConst(token);
                token="";
            }
            else if(ch=='*'){  //是否是星号
                insertStar(ch);
            }
            else if(ch=='='){  //是否是等号
                insertEqual(ch);
            }
            else if(isSeparator(ch)){
                insertSeparators(ch);
            }
            p++;
        }
        String loggerInfo="词法分析：";
        for(int i=0;i<words.size();i++){
            loggerInfo+=words.get(i).name+":"+words.get(i).type+"\n";
        }
        logger.info(loggerInfo);
    }


    boolean isSeparator(char ch){
        if(ch=='('||ch=='\''||ch==','||ch==')'){
            return true;
        }
        return false;
    }

    void insertSeparators(char ch){
        String str=""+ch;
        word newWord=new word(str,7);
        words.add(newWord);
    }

    void insertEqual(char ch){
        String str=""+ch;
        word newWord=new word(str,6);
        words.add(newWord);
    }

    void insertStar(char ch) {
        String str=""+ch;
        word newWord=new word(str,5);
        words.add(newWord);
    }

    void insertConst(String str){
        word newWord=new word(str,4);
        words.add(newWord);
    }

    void insertKeyWords(String str){
        word newWord=new word(str,1);
        words.add(newWord);
    }

    void insertDataType(String str){
        word newWord=new word(str,2);
        words.add(newWord);
    }

    void insertId(String str){
        word newWord=new word(str,3);
        words.add(newWord);
    }

    boolean isDataType(String str)
    {
        for(int i = 0;i < DataType.size();i++)
        {
            if(DataType.get(i).equals(str))
                return true;
        }
        return false;
    }

    boolean isKeyWord(String str)
    {
        for(int i = 0;i < keyWord.size();i++)
        {
            if(keyWord.get(i).equals(str))
                return true;
        }
        return false;
    }
    //判断是否是字母
    boolean isLetter(char letter)
    {
        if((letter >= 'a' && letter <= 'z'))
            return true;
        else
            return false;
    }
    //判断是否是数字
    boolean isDigit(char digit)
    {
        if(digit >= '0' && digit <= '9')
            return true;
        else
            return false;
    }
}
