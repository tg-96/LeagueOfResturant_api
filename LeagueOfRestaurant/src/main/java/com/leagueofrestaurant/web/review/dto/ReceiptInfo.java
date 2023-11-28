package com.leagueofrestaurant.web.review.dto;

public class ReceiptInfo {
    private String storeName;
    private String address;
    public static ReceiptInfo create(String storeName, String address){
        ReceiptInfo receiptInfo = new ReceiptInfo();
        receiptInfo.storeName = storeName;
        receiptInfo.address = address;
        return receiptInfo;
    }
}
