package com.example.demo.dao.Impl;

import com.example.demo.POJO.Good;
import com.example.demo.POJO.SortedGood;
import com.example.demo.dao.goodsDao;
import com.example.demo.dao.sortedGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现物品的底层算法：
 * sort:根据到达时间和是否是VIP进行排序
 * select:根据是否是VIP和是否已送出进行选择
 * dijkstra:最优路径计算
 * Good_en2ch:将物品信息中的英文转换成中文
 */

@Repository
public class sortedGoodsImpl implements sortedGoodsDao {

    @Autowired
    goodsDao goodsDao;

    /**
     * 按照到站时间进行排序，Vip会先到一天
     * @param goodsList
     * @return
     */

    @Override
    public List sort(List<SortedGood>goodsList){
        for(int i=0;i<goodsList.size()-1;i++) {
            for(int j=i+1;j<goodsList.size();j++) {
                String strTime1 = goodsList.get(i).getArriveTime();
                String strTime2 = goodsList.get(j).getArriveTime();
                long longTime1 = Long.parseLong(strTime1);
                long longTime2 = Long.parseLong(strTime2);
                if(goodsList.get(i).getIsVip().equals("是")){
                    longTime1-=10000;
                }
                if(goodsList.get(j).getIsVip().equals("是")){
                    longTime2-=10000;
                }
                if(longTime1>longTime2){
                    SortedGood tempGood=goodsList.get(i);
                    goodsList.set(i,goodsList.get(j));
                    goodsList.set(j,tempGood);
                }
            }
        }
        return goodsList;
    }

    /**
     * 筛选数据，按照Choice数据大小进行选择
     * @param goodsList
     * @param choice
     * @return
     */

    @Override
    public List select(List<SortedGood>goodsList,int choice){
        List<SortedGood>SortedGoodsList=new ArrayList<>();
        if(choice==0) {
            for (int i = 0; i < goodsList.size(); i++) {
                if (goodsList.get(i).getIsVip().equals("是"))
                    SortedGoodsList.add(goodsList.get(i));
            }
        }else if(choice==1){
            for (int i = 0; i < goodsList.size(); i++) {
                if (goodsList.get(i).getIsSend().equals("是"))
                    SortedGoodsList.add(goodsList.get(i));
            }
        }else{
            for (int i = 0; i < goodsList.size(); i++) {
                if (goodsList.get(i).getIsSend().equals("否"))
                    SortedGoodsList.add(goodsList.get(i));
            }
        }
        return SortedGoodsList;
    }

    /**
     * 迪杰斯特拉算法实现，查找最优路径
     * @param cost
     * @return
     */

    @Override
    public List dijkstra(int [][]cost){
        int deliveryAddress=1,cityNum=cost[0].length,INF=1000;
        int[] dist=new int[cityNum];
        boolean[] flag = new boolean[cityNum];
        String[] road=new String[cityNum];
        List<SortedGood> SortedGoods=Goods_en2ch();
        String[]destination_ch=new String[]{"长沙","成都","重庆","广州","杭州","南京","上海","武汉"};
        for (int i = 0; i < cityNum; i++) {
            flag[i] = false;
            dist[i] = cost[deliveryAddress][i];
            road[i]=destination_ch[deliveryAddress]+"-->"+destination_ch[i];
        }
        flag[deliveryAddress] = true;
        int k=0;
        for (int i = 0; i < cityNum; i++) {
            int min = INF;
            for (int j = 0; j <cityNum; j++) {
                if (flag[j]==false && dist[j]<min) {
                    min = dist[j];
                    k = j;
                }
            }
            flag[k] = true;
            for (int j = 0; j < cityNum; j++) {
                int tmp = (cost[k][j]==INF ? INF : (min + cost[k][j]));
                if (flag[j]==false && (tmp<dist[j]) ) {
                    dist[j] = tmp;
                    road[j]=road[k]+"-->"+destination_ch[j];
                }
            }
        }

        for(int i=0;i<SortedGoods.size();i++){
            for(int j=0;j<destination_ch.length;j++) {
                if (SortedGoods.get(i).getDestination().equals(destination_ch[j])) {
                    SortedGoods.get(i).setRoad(road[j]);
                    SortedGoods.get(i).setCost(" "+dist[j]);
                    break;
                }
            }
        }
        return SortedGoods;
    }

    /**
     * 中英文转换
     * @return
     */

    private List Goods_en2ch(){
        String[]destination_ch=new String[]{"长沙","成都","重庆","广州","杭州","南京","上海","武汉"};
        String[]destination_en=new String[]{"Changsha","Chengdu","Chongqing","Guangzhou","Hangzhou","Nanjing","Shanghai","Wuhan"};
        List<Good> goodsList=goodsDao.read();
        List<SortedGood> SortedGoodList=new ArrayList<>();
        for(int i=0;i<goodsList.size();i++){
            for(int j=0;j<destination_en.length;j++){
                if(destination_en[j].equals(goodsList.get(i).getDestination())){
                    goodsList.get(i).setDestination(destination_ch[j]);
                    break;
                }
            }
        }
        for(int i=0;i<goodsList.size();i++){
            SortedGood SortedGood=new SortedGood(goodsList.get(i));
            SortedGoodList.add(SortedGood);
        }
        return SortedGoodList;
    }

}
