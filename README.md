# League Of Restaurant
![image](https://github.com/tg-96/LeagueOfResturant_api/assets/98454438/4720bc8d-61c6-46c0-97d6-5b0ed071ba0c)

## 서비스 소개
점심시간, 나가서 사먹어야 하는데 마땅히 먹을게 없다. 여러 서비스에 맛집이라고 올라와 있는 음식점들도 사실은 광고,협찬,믿을 수 없는 리뷰가 많이 있어 신뢰하기가 힘들다.
광고나 협찬이 아닌 내 주변 사람들이 실제로 추천하는 맛집을 만들 수 있지 않을까? 하는 생각에서 만들었습니다. 
- 영수증 인증을 통해 실제 이용자만 등록할 수 있는 리뷰
- 가게의 첫 리뷰를 통해 가게가 서비스에 자동으로 등록 되므로 소비자가 인정한 맛집이라는 사실 증명
- 예전에 맛있었던 맛집도 현재는 아닐 수 있다. 시즌제(분기별) 도입으로 맛집 최신화
- 한 시즌동안 동일한 가게는 한번만 리뷰 가능
- 사람마다 다른 별점의 기준, 확실한 가이드라인을 통해 별점 객관화
  
## 핵심 요구사항
### 사용자
- 가게 랭킹 & 가게 리뷰 조회
- 가게 상세 정보 조회
- 영수증 인증 & 리뷰 작성
- 가게 찜하기
- 리뷰 신
### 시스템
- OCR 영수증 인식을 통해 가게 생성 & 리뷰 작성 혹은 이미 리뷰가 있는 가게일 경우 리뷰만 작성 
- 가게 생성 단계에서 크롤링을 통해 가게 이미지 db에 저장
- 시즌이 끝난 시간에 현재 시즌의 상위 10개 가게를 명예의 전당 카테고리로 이동
- 가게 랭킹선정을 위한 리뷰수+별점으로 만들어진 알고리즘
- 지도 api를 통해 서비스에 등록되어 있는 가게 조회

## Use Case Diagram 
![image](https://github.com/tg-96/LeagueOfResturant_api/assets/98454438/b2405288-6a3d-47e4-b67a-1335610c3ea6)
  
## ERD
![image](https://github.com/tg-96/LeagueOfResturant_api/assets/98454438/02b5b0d4-c02a-44dc-8e40-a3a0b132a611)

## 기술 스택
![image](https://github.com/tg-96/LeagueOfResturant_api/assets/98454438/11fc6f54-7233-4dc9-965d-c04e98356d2c)

## 협업 방식
- github, discord
- branch 관리:
  main(배포) - dev(개발) - feat/{...}

## 배포 
- aws : EC2, RDS (현재는 모두 삭제)

## API

### MemberController

#### GET 모든멤버조회
##### Request
```
curl --location 'http://localhost:8080/members'  
```
##### Request
``` 
{
    "name": "John Doe",
    "phoneNumber": "1234567890",
    "password": "$2a$10$Z5Ii8JyEJXPVeqOHCX8xkuQrvI8GMNH2JEs0r79jlAoY/bBJTPl8m",
    "gender": "MALE",
    "birthday": "1990-01-01"
}
```
#### GET 로그인한 멤버 정보 조회
##### Request
```
curl --location 'http://localhost:8080/members/member'
```
##### Response (성공) 200 ok
```
{
    "name": "John Doe",
    "phoneNumber": "1234567890",
    "password": "$2a$10$Z5Ii8JyEJXPVeqOHCX8xkuQrvI8GMNH2JEs0r79jlAoY/bBJTPl8m",
    "gender": "MALE",
    "birthday": "1990-01-01"
}
```
##### Response (실패) 400 BAD REQUEST
```
{
    "errorCode": "NO_SESSION",
    "message": "로그인을 하지 않았습니다."
}
```
#### PUT 로그인 중인 회원정보 수정 요청
##### Request
```
curl --location --request PUT 'http://localhost:8080/members/edit' \
--data '{
        "name" : "alli",
        "password" : "1234",
        "gender" : "FEMALE",
        "birthday" : "2000-12-30"
}'
```
##### Response 200 ok
#### DELETE 회원탈퇴
##### Request
```
curl --location --request DELETE 'http://15.165.26.32:8080/members/delete'
```
##### Response 200 ok
#### GET 인가(로그인 상태인지 확인)
##### Request
```
curl --location 'http://localhost:8080/members/auth'
```
##### Response(로그인 상태)
200 ok
##### Response(로그인 안한 상태) 400 BAD REQUEST
```
{
    "errorCode": "NO_SESSION",
    "message": "로그인을 하지 않았습니다."
}
```
#### GET 핸드폰 번호 중복 검사
##### Request
```
curl --location 'http://15.165.26.32:8080/members/dupCheck/01056784567'
```
##### Response(핸드폰 번호가 이미 존재) 400 BAD REQUEST
```
{
    "errorCode": "PHONE_NUM_DUPLICATED",
    "message": "핸드폰 번호가 이미 존재합니다."
}
```
##### Response(핸드폰 번호 존재)
200 ok
##### Response(잘못된 형식으로 입력)
```
{
    "timestamp": "2023-12-02T10:33:26.719+00:00",
    "status": 500,
    "error": "Internal Server Error",
    ....
    ....
    "message": "PhoneNumberDupCheck.phoneNumber: 010으로 시작하고, '-' 없이 입력해주세요.",
    "path": "/members/dupCheck/011124325757"
}
```
### LoginController

#### POST 회원가입
##### Request
```
curl --location 'http://localhost:8080/join' \
--data '{
    "name": "John Doe",
    "phoneNumber": "123-456-7890",
    "password": "password123",
    "gender": "MALE",
    "birthday": "1990-01-01"
  }'
```
##### Response(성공) 200 ok
##### Response(실패) 400 BAD REQUEST
```
{
    "errorCode": "ALREADY_EXISTS_USER",
    "message": "이미 있는 계정"
}
```

#### POST 로그인
##### Request
```
curl --location 'http://localhost:8080/login' \
--data '{
    "phoneNumber" : "1234567890",
    "password": "password123"
}'
```
##### Response(로그인 성공) 200 ok
##### Response(패스워드 일치하지 않음) 400 BAD REQUEST
```
{
    "errorCode": "PASSWORD_NOT_MATCHED",
    "message": "일치하지 않는 비밀번호"
}
```
##### Response(존재하지 않는 핸드폰 번호) 400 BAD REQUEST
```
{
    "errorCode": "NOT_EXIST_PHONE_NUMBER",
    "message": "존재하지 않는 핸드폰 번호"
}
```
#### POST 로그아웃
##### Request
```
curl --location 'http://localhost:8080/logout'
```
##### Response 200 ok

### ReviewController
#### POST 영수증 인식
##### Request
```
curl --location 'http://localhost:8080/reviews/receipt/' \
--form 'image=@"영수증테스트/tom.png"'
```
##### Response 200 ok
```
{
    "storeName": "탐앤탐스 아주대점",
    "address": "경기도 수원시 영통구 아주로 46 (원천동) 아록빌딩 1층"
}
```
#### POST 리뷰 생성
##### Request
```
curl --location 'http://localhost:8080/reviews/' \
--form 'reviewDto="{\"storeName \" : \"탐앤탐스 아주대점\", \"content\" : \"맛있어요!!\", \"ratingPoint\" : \"5\", \"address\" : \"경기도 수원시 영통구 아주로 46 (원천동) 아록빌딩 1층\"}";type=application/json' \
--form 'image=@"ajouuniv/2023-2/소프트웨어공학/프로젝트/영수증테스트/tom.png"'
```
##### Response 200 ok

#### GET 전체 리뷰 조회
##### Request
```
curl --location 'http://localhost:8080/reviews/'
```
##### Response
```
[
    {
        "storeName" : "가장 맛있는 족발",
        "content": "굿굿굿",
        "ratingPoint": 4,
        "img": "src/main/resources/static/images/22532686-4243-460f-8b5c-410821020153.jpeg",
        "season": "2023-Fall",
        "reviewId" : "1"
    },
    {
        "storeName" : "순대국밥",
        "content": "맛있어요!",
        "ratingPoint": 3,
        "img": null,
        "season": "2023-Fall",
        "reviewId" : "2"
    }
]
```
#### GET 특정 리뷰 조회
##### Request
```
curl --location 'http://localhost:8080/reviews/1'
```
##### Response 200 ok
```
{
    "storeName" : "가장 맛있는 족발",
    "content": "굿굿굿",
    "ratingPoint": 4,
    "img": "src/main/resources/static/images/22532686-4243-460f-8b5c-410821020153.jpeg",
    "season": "2023-Fall",
    "reviewId" : "1"
}
```
#### GET 특정 가게 리뷰 조회
##### Request
```
curl --location 'http://localhost:8080/reviews/store/229?season=2023-Winter&page=0&size=2'
```
##### Response
```
[
    {
        "storeName" : "가장 맛있는 족발",
        "content": "굿굿굿",
        "ratingPoint": 4,
        "img": "src/main/resources/static/images/43332686-4243-460f-8b5c-410821020153.jpeg",
        "season": "2023-Fall",
        "reviewId" : "1"
    },
    {
        "storeName" : "가장 맛있는 족발",
        "content": "맛있어요!",
        "ratingPoint": 5,
        "img": "src/main/resources/static/images/96753213-4523-980c-8d7d-392809871837.jpeg",
        "season": "2023-Fall",
        "reviewId" : "11"
    }
]
```
#### GET 현재 로그인한 회원의 리뷰 조회
##### Request
```
curl --location 'http://localhost:8080/reviews/member/'
```
##### Response
```
[
    {
        "storeName" : "가장 맛있는 족발",
        "content": "굿굿굿",
        "ratingPoint": 4,
        "img": "src/main/resources/static/images/43332686-4243-460f-8b5c-410821020153.jpeg",
        "season": "2023-Fall",
        "reviewId" : "1"
    },
    {
        "storeName" : "수원 왕갈비 통닭",
        "content": "와 진짜 맛있어요",
        "ratingPoint": 5,
        "img": "src/main/resources/static/images/39847643-2736-089c-7a8b-087384627364.jpeg",
        "season": "2023-Fall",
        "reviewId" : "1"
    },
]
```

#### DELETE 특정 리뷰 삭제
##### Request
```
curl --location --request DELETE 'http://localhost:8080/reviews/22'
```
##### Response 200 ok

### ReportController

#### POST 신고 생성
##### Request
```
curl --location 'http://localhost:8080/reports/' \
--data '{
    "reviewId": 5,
    "memberId": 2,
    "type": "UNRELATED_CONTENT",
    "content": "이상한내용이있어여"
}'
```
##### Response 200 ok

#### PUT 신고 처리상태 변경
##### Request(완료로 바꾸기)
```
curl --location --request PUT 'http://localhost:8080/reports/23' \
--data '{
    "status": "PROCESSING"
}'
```
##### Request(처리중으로 바꾸기)
```
curl --location --request PUT 'http://localhost:8080/reports/23' \
--data '{
    "status": "PROCESSING"
}'
```
##### Response 200 ok

#### GET 전체 신고 조회
##### Request
```
curl --location 'http://localhost:8080/reports/'
```
##### Response
```
[
    {
        "type": UNRELATED_CONTENT,
        "content": "내용이 상관없는것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OBSCENE_LANGUAGE",
        "content": "비속어가 섞여 있어요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "PRIVACY_RISK",
        "content": "개인정보가 유출될 것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "INAPPROPRIATE_AD",
        "content": "광고목적인 것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OTHER",
        "content": "말도 안되는 억지를 부려요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OTHER",
        "content": "이상한것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "UNRELATED_CONTENT",
        "content": "이상한내용이있어여",
        "memberId": 2,
        "reviewId": 5
    }
]
```
#### GET 특정 유저의 신고 조회
##### Request
```
curl --location 'http://localhost:8080/reports/member/2'
```
##### Response
```
[
  {
    "type": "UNRELATED_CONTENT",
    "content": "이상한내용이있어여",
    "memberId": 2,
    "reviewId": 5
  }
]
```
#### GET 처리 상태로 신고 조회
##### Requset (처리중인 신고목록 조회)
```
curl --location 'http://localhost:8080/reports/status/PROCESSING'
```
##### Response
```
 {
        "type": UNRELATED_CONTENT,
        "content": "내용이 상관없는것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OBSCENE_LANGUAGE",
        "content": "비속어가 섞여 있어요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "PRIVACY_RISK",
        "content": "개인정보가 유출될 것 같아요",
        "memberId": 2,
        "reviewId": 5
    }
```
 
##### Request(처리 완료된 신고 목록)
```
curl --location 'http://localhost:8080/reports/status/COMPLETED'
```
##### Response
```
{
        "type": "INAPPROPRIATE_AD",
        "content": "광고목적인 것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OTHER",
        "content": "말도 안되는 억지를 부려요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "OTHER",
        "content": "이상한것 같아요",
        "memberId": 2,
        "reviewId": 5
    },
    {
        "type": "UNRELATED_CONTENT",
        "content": "이상한내용이있어여",
        "memberId": 2,
        "reviewId": 5
    }
```

### StoreController

#### GET 특정 가게정보 조회
##### Request
```
curl --location 'http://localhost:8080/store/171'
```
##### Response
```
{
    "id": 171,
    "name": "육회자매집 본점",
    "address": "서울특별시 종로구 종로 200-4",
    "city": "서울",
    "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF"
}
```

#### GET 조건으로 가게 조회
##### Request
```
curl --location 'http://localhost:8080/stores/condition?address=서울특별시 종로구 종로 200-4&city=서울&name=육회자매집 본점'
```
##### Response
```
{
    "id": 171,
    "name": "육회자매집 본점",
    "address": "서울특별시 종로구 종로 200-4",
    "city": "서울",
    "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF"
}
```

#### GET 지역별 가게 리스트 조회(지도)
##### Request
```
curl --location 'http://localhost:8080/stores/map/수원'
```
##### Response
```
[
    {
        "id": 6,
        "name": "겐코 아주대점",
        "address": "경기 수원시 팔달구 아주로47번길 12",
        "city": "수원",
        "img": "https://t1.daumcdn.net/localfiy/72EA67D991F147AF82546F92B53C697E"
    },
    {
        "id": 8,
        "name": "사랑집 아주대점",
        "address": "경기도 수원시 팔달구 우만동 아주로39번길 18-7",
        "city": "수원",
        "img": "https://t1.kakaocdn.net/mystore/4E654F81E87A4258A1EBB96846B72C6F"
    },
    {
        "id": 10,
        "name": "김형건술집",
        "address": "경기도 수원시 영통구 아주로 4번길 18",
        "city": "수원",
        "img": "https://t1.daumcdn.net/place/8E211424C2D14EF3A9DD8A529F5E73FA"
    }
]
```
#### GET 지역별 가게 랭킹 페이지 조회
##### Request
```
curl --location 'http://localhost:8080/stores/rank/서울?page=0&size=5'
```
##### Response
```
[
    {
        "id": 171,
        "name": "육회자매집 본점",
        "address": "서울특별시 종로구 종로 200-4",
        "city": "서울",
        "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF"
    },
    {
        "id": 223,
        "name": "정식당",
        "address": "서울 강남구 선릉로158길 11",
        "city": "서울",
        "img": "https://t1.daumcdn.net/place/B4BD18D8B9DA42B785422BCB69532AB7"
    },
    {
        "id": 225,
        "name": "신라호텔 라연",
        "address": "서울 중구 동호로 249",
        "city": "서울",
        "img": "https://t1.daumcdn.net/cfile/1563013B4F547AAC0D"
    }
]
```
### WishController

#### PUT 특정 가게 찜하기 상태 변경
##### Request
```
curl --location 'http://localhost:8080/wish/change/:storeId'
```
##### Response 200 ok
찜한 상태라면 db에서 제거, 찜이 되어 있지 않다면 db에 저장

#### GET 현재 회원의 찜하기 목록 조회
##### Request
```
curl --location 'http://localhost:8080/wishes'
```  
##### Response
```
[
  {
      "storeId": 171,
      "name": "육회자매집 본점",
      "address": "서울특별시 종로구 종로 200-4",
      "city": "서울",
      "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF",
      "rating" : "4",
      "score"  : "720"
  }
]
```
#### GET 현재 회원의 특정 가게 찜유무 조회
##### Requset
```
curl --location 'http://localhost:8080/wish/state/171'
```
##### Response 200 ok
```
true
```

### SeasonRankController

#### GET 특정 가게의 명예의 전당 랭킹 이력 조회
##### Requset
```
curl --location 'http://localhost:8080/seasonRank/store/171'
```
##### Response
```
[
  {
      "storeName": "육회자매집 본점",  
      "storeId": 171,
      "city": "서울",
      "season" : "2023-Fall",
      "ranking" : "3",
      "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF"
  }
]
```

#### GET 지역별 특정 시즌 명예의 전당 리스트 조회
##### Request
```
curl --location 'http://localhost:8080/seasonRank/2023-Fall/서울'
```
##### Response
```
[
  {
      "storeName": "육회자매집 본점",  
      "storeId": 171,
      "city": "서울",
      "season" : "2023-Fall",
      "ranking" : "3",
      "img": "https://t1.daumcdn.net/place/059AA426385F4953B5AFBEF5F29494DF"
  }
]
```
#### GET 명예의 전당에 있는 시즌 이름들 조회
##### Requset
```
curl --location 'http://localhost:8080/seasonRank/seasonName'
```
##### Response
```
[
  "2023-Fall",
  "2023-Summer"
]
```
#### GET 현재 시즌 조회
##### Request
```
curl --location 'http://localhost:8080/seasonRank/now'
```
##### Response
```
"2023-Winter"
```

#### GET 다음 시즌까지의 날짜, 시간 조회
##### Request
```
curl --location 'http://localhost:8080/seasonRank/next'
```
##### Response
```
{
    "days": 89,
    "hours": 0
}
```
