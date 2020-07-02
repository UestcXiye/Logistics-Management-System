package com.example.demo.controller;

import com.example.demo.POJO.Good;
import com.example.demo.POJO.SortedGood;
import com.example.demo.POJO.Time;
import com.example.demo.service.DeliveryStateService;
import com.example.demo.service.RoadPlanService;
import com.example.demo.service.ShowInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息查找控制层
 */

@RestController
@RequestMapping("/search")
public class SearchController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private DeliveryStateService deliveryStateService;

    @Autowired
    private ShowInfoService showInfoService;

    @Autowired
    private RoadPlanService roadPlanService;

    /**
     * 物流状态查找
     * @param OrderNumber
     * @return
     */

    @GetMapping("/StatusQuery")
    public String StatusQuery(String OrderNumber){
        String status=deliveryStateService.delivery_state(OrderNumber);
        logger.info("查询物流状态");
        return status;
    }

    /**
     * 物品查询
     * @param OrderNumber
     * @return
     */

    @GetMapping("/GoodQuery")
    public Good GoodQuery(String OrderNumber){
        Good searchGood=showInfoService.Goods_search(OrderNumber);
        logger.info("查询物品");
        return searchGood;
    }

    /**
     * 物流节点查询
     * @param CityName
     * @return
     */

    @GetMapping("/NodeQuery")
    public Time NodeQuery(String CityName){
        String[]destination_ch=new String[]{"长沙","成都","重庆","广州","杭州","南京","上海","武汉"};
        String[]destination_en=new String[]{"Changsha","Chengdu","Chongqing","Guangzhou","Hangzhou","Nanjing","Shanghai","Wuhan"};
        for(int i=0;i<destination_ch.length;i++){
            if(CityName.equals(destination_ch[i])){
                CityName=destination_en[i];
                break;
            }
        }
        Time Cost=showInfoService.Nodes_search(CityName);
        logger.info("查询物流节点");
        return Cost;
    }

    /**
     * 物品到达成都的时间排序
     * @return
     */

    @GetMapping("/TimeSort")
    public List TimeSort(){
        List<SortedGood> SortedTime= roadPlanService.MinTime();
        SortedTime=roadPlanService.Sort(SortedTime);
        logger.info("到达时间排序");
        return SortedTime;
    }

    /**
     * 最少时间方案
     * @return
     */

    @GetMapping("/MinTime")
    public List MinTime(){
        List<SortedGood> SortedTime= roadPlanService.MinTime();
        logger.info("最短时间方案");
        return SortedTime;
    }

    /**
     * 最少费用方案
     * @return
     */

    @GetMapping("/MinCost")
    public List MinCost(){
        List<SortedGood> SortedCost= roadPlanService.MinCost();
        logger.info("最少花费方案");
        return SortedCost;
    }

    /**
     * 综合最优方案
     * @return
     */

    @GetMapping("/MinGeneral")
    public List MinGeneral(){
        List<SortedGood> SortedGeneral= roadPlanService.MinGeneral();
        logger.info("综合最优方案");
        return SortedGeneral;
    }

    /**
     * 筛选已寄出的物品
     * @return
     */

    @GetMapping("/IsSend")
    public List IsSend(){
        List<SortedGood> SortedCost= roadPlanService.MinCost();
        SortedCost=roadPlanService.Select(SortedCost,1);
        logger.info("查找已发货");
        return SortedCost;
    }

    /**
     * 筛选还没有寄出的物品
     * @return
     */

    @GetMapping("/NotSend")
    public List NotSend(){
        List<SortedGood> SortedCost= roadPlanService.MinCost();
        SortedCost=roadPlanService.Select(SortedCost,2);
        logger.info("查找未发货");
        return SortedCost;
    }

    /**
     * 筛选Vip
     * @return
     */

    @GetMapping("/IsVip")
    public List IsVip(){
        List<SortedGood> SortedTime= roadPlanService.MinTime();
        SortedTime=roadPlanService.Select(SortedTime,0);
        logger.info("查找VIP");
        return SortedTime;
    }

    /**
     * 所有物流节点信息展示
     * @return
     */

    @GetMapping("/NodesInformation")
    public List NodesInformation(){
        List<Time> Nodes= showInfoService.Nodes_print();
        logger.info("节点信息显示");
        return Nodes;
    }
}
