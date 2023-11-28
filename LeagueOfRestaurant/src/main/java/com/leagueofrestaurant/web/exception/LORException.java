package com.leagueofrestaurant.web.exception;

import lombok.Getter;

@Getter
public class LORException extends RuntimeException{
    private final ErrorCode errorCode;

    public LORException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
