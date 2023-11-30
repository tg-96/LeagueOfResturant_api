package com.leagueofrestaurant.web.report.domain;

public enum Type {
    UNRELATED_CONTENT("가게와 관련 없는 내용"),
    OBSCENE_LANGUAGE("음란성, 욕설"),
    PRIVACY_RISK("개인정보 유출 위험"),
    INAPPROPRIATE_AD("부적절한 홍보 또는 광고"),
    OTHER("기타");

    private final String displayName;

    Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
