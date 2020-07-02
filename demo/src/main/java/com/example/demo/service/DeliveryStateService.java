package com.example.demo.service;

/**
 * delivery_state: 物品物流状态函数
 * SendOut:发货
 */
public interface DeliveryStateService {
    public String delivery_state(String OrderNumber);
    public boolean SendOut(String OrderNumber);
}
