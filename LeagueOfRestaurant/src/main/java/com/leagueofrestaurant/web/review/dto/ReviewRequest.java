package com.leagueofrestaurant.web.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewRequest {
    private long memberId;
    private ReviewContent reviewContent;
    private ReceiptInfo receiptInfo;
}
