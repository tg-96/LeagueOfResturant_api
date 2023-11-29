package com.leagueofrestaurant.web.review.dto;
import com.leagueofrestaurant.web.store.domain.Address;
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
    private Address address;
}
