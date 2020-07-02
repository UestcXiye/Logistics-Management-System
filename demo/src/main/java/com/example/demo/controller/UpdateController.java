package com.example.demo.controller;

import com.example.demo.POJO.Cost;
import com.example.demo.POJO.Good;
import com.example.demo.POJO.Time;
import com.example.demo.dao.costDao;
import com.example.demo.dao.timeDao;
import com.example.demo.service.DeliveryStateService;
import com.example.demo.service.UpdateInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据更新Controller层
 */

@RestController
@RequestMapping("/update")
public class UpdateController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private costDao costDao;

    @Autowired
    private timeDao timeDao;

    @Autowired
    private DeliveryStateService deliveryStateService;

    @Autowired
    private UpdateInfoService updateInfoService;

    /**
     * 插入货物
     * @param NewGood
     * @return String
     */

    @GetMapping("/InsertInfo")
    public String InsertInfo(Good NewGood){
        String[] destination_ch = new String[]{"长沙", "成都", "重庆", "广州", "杭州", "南京", "上海", "武汉"};
        String[] destination_en = new String[]{"Changsha", "Chengdu", "Chongqing", "Guangzhou", "Hangzhou", "Nanjing", "Shanghai", "Wuhan"};
        for(int i=0;i<destination_ch.length;i++){
            if(destination_ch[i].toLowerCase().equals(NewGood.getDestination().toLowerCase())){
                NewGood.setDestination(destination_en[i]);
            }
        }
        if(NewGood.getIsVip().equals("是")){ NewGood.setIsVip("true"); }
        if(NewGood.getIsSend().equals("是")){ NewGood.setIsSend("true"); }
        if(NewGood.getIsVip().equals("否")){ NewGood.setIsVip("false"); }
        if(NewGood.getIsSend().equals("否")){ NewGood.setIsSend("false"); }
        boolean success= updateInfoService.goodsNodeInsert(NewGood);
        if(success==true){
            logger.info("插入物品信息");
            return "true";
        }else{ return ""; }
    }

    /**
     * 更新货物
     * @param NewGood
     * @return
     */

    @GetMapping("/ModifyInfo")
    public String ModifyInfo(Good NewGood){
        String[] destination_ch = new String[]{"长沙", "成都", "重庆", "广州", "杭州", "南京", "上海", "武汉"};
        String[] destination_en = new String[]{"Changsha", "Chengdu", "Chongqing", "Guangzhou", "Hangzhou", "Nanjing", "Shanghai", "Wuhan"};
        for(int i=0;i<destination_ch.length;i++){
            if(destination_ch[i].toLowerCase().equals(NewGood.getDestination().toLowerCase())){
                NewGood.setDestination(destination_en[i]);
            }
        }
        if(NewGood.getIsVip().equals("是")){ NewGood.setIsVip("true"); }
        if(NewGood.getIsSend().equals("是")){ NewGood.setIsSend("true"); }
        if(NewGood.getIsVip().equals("否")){ NewGood.setIsVip("false"); }
        if(NewGood.getIsSend().equals("否")){ NewGood.setIsSend("false"); }
        boolean success= updateInfoService.goodsNodeUpdate(NewGood);
        if(success==true){
            logger.info("修改物品信息");
            return "true";
        }else{ return ""; }
    }

    /**
     * 删除货物
     * @param OrderNumber
     * @return
     */

    @GetMapping("/DeleteInfo")
    public String DeleteInfo(String OrderNumber){
        boolean success= updateInfoService.goodsNodeDelete(OrderNumber);
        if(success==true){
            logger.info("删除物品信息");
            return "true";
        }else{ return ""; }
    }

    /**
     * 更新物流节点信息
     * @param CityName
     * @param Destination
     * @param Value
     * @param Choice
     * @return
     */

    @GetMapping("/UpdateNode")
    public String UpdateNode(String CityName,String Destination,String Value,String Choice) {
        String[] destination_ch = new String[]{"长沙", "成都", "重庆", "广州", "杭州", "南京", "上海", "武汉"};
        String[] destination_en = new String[]{"Changsha", "Chengdu", "Chongqing", "Guangzhou", "Hangzhou", "Nanjing", "Shanghai", "Wuhan"};
        boolean success = false;
        for (int i = 0; i < destination_ch.length; i++) {
            if (CityName.equals(destination_ch[i])) {
                if (Choice.equals("0")) {
                    Time UpdateTime = timeDao.search(destination_en[i]);
                    for (int j = 0; j < destination_ch.length; j++) {
                        if (Destination.equals(destination_ch[j])) {
                            UpdateTime.setCost(Value, j);
                            break;
                        }
                    }
                    success = updateInfoService.timeNodeUpdate(UpdateTime);
                } else {
                    Cost UpdateCost = costDao.search(destination_en[i]);
                    for (int j = 0; j < destination_ch.length; j++) {
                        if (Destination.equals(destination_ch[j])) {
                            UpdateCost.setCost(Value, j);
                            break;
                        }
                    }
                    success = updateInfoService.costNodeUpdate(UpdateCost);
                }
                break;
            }
        }
        if (success == true) {
            logger.info("物流节点信息修改成功");
            return "true";
        } else {
            return "";
        }
    }

    /**
     * 删除物流节点信息
     * @param CityName
     * @return
     */

    @GetMapping("/DeleteNode")
    public String DeleteNode(String CityName){
        boolean successCost= updateInfoService.costNodeDelete(CityName);
        boolean successTime=updateInfoService.timeNodeDelete(CityName);
        if(successCost==true||successTime==true){
            logger.info("删除物流信息");
            return "true";
        }else{ return ""; }
    }

    /**
     * 发货
     * @param OrderNumber
     * @return
     */

    @GetMapping("/SendOut")
    public String SendOut(String OrderNumber){
        boolean success= deliveryStateService.SendOut(OrderNumber);
        if(success==true){
            logger.info("发货成功");
            return("true");
        }else{
            logger.info("货物已发出或者没有此货物");
            return("");
        }
    }
}
