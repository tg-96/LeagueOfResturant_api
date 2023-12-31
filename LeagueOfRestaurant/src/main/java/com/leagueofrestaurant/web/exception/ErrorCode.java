package com.leagueofrestaurant.web.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_EXIST_PHONE_NUMBER("존재하지 않는 핸드폰 번호"),
    ALREADY_EXISTS_USER("이미 있는 계정"),
    PASSWORD_NOT_MATCHED("일치하지 않는 비밀번호"),
    FAIL_TO_DELETE("유저 정보 삭제 실패"),
    FAIL_TO_DELETE_REVIEW("리뷰 삭제 실패"),
    PHONE_NUM_DUPLICATED("핸드폰 번호가 이미 존재합니다."),
    NO_SESSION("로그인을 하지 않았습니다."),
    NO_EXIST_STORE("조회할 store가 존재하지 않습니다."),
    NOT_EXIST_REVIEW("존재하지 않는 리뷰"),
    NOT_EXIST_MEMBER("존재하지 않는 회원"),
    NOT_EXIST_REPORT("존재하지 않는 신고내역"),
    WRONG_MEMBER_OR_STORE_ID("member id 혹은 store id가 잘못 되었습니다."),
    NO_WISHLIST("조회할 위시리스트가 없습니다."),
    RECEIPT_ERROR("영수증 인식 오류"),
    FAIL_TO_CREATE_STORE("가게 생성 오류"),
    DUPLICATE_REVIEW("이미 해당 가게에 리뷰를 작성함"),
    NOT_SUPPORT_AREA("지원하지 않는 지역"),
    NO_EXIST_PRE_RANKING("이전 시즌 랭킹에 해당 가게가 없습니다."),
    BLANK_PHONE_NUMBER("핸드폰 번호를 입력해주세요."),
    WRONG_FORMAT_PHONENUMBER("잘못된 형식의 전화번호 입니다.");

    private final String message;
    ErrorCode(String message) {
        this.message = message;
    }
}
