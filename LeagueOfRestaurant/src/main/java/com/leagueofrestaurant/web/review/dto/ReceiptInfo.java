package com.leagueofrestaurant.web.review.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReceiptInfo {
    private String storeName;
    private String address;
}
