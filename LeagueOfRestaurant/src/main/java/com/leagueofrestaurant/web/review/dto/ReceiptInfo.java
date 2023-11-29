package com.leagueofrestaurant.web.review.dto;

import com.leagueofrestaurant.web.store.domain.Address;

public class ReceiptInfo {
    private String storeName;
    private Address address;
    public static ReceiptInfo createReceipt(String storeName, Address address){
        ReceiptInfo receiptInfo = new ReceiptInfo();
        receiptInfo.storeName = storeName;
        receiptInfo.address = address;
        return receiptInfo;
    }
}
