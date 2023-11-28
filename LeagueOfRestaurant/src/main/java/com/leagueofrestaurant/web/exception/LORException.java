package com.leagueofrestaurant.web.exception;

import lombok.Getter;

@Getter
public class DuplicatedException extends RuntimeException{
    private final ErrorCode errorCode;

    public DuplicatedException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
