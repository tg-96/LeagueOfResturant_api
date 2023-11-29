package com.leagueofrestaurant.web.review.dto;
import org.apache.tomcat.jni.Address;

public class ReceiptInfo {
    private String storeName;
    private String Address;
    private String city;

    public ReceiptInfo(String storeName, String address, String city) {
        this.storeName = storeName;
        Address = address;
        this.city = city;
    }
}
