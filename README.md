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
- branch 관리
  main(배포) - dev(개발) - feat/{...}

## 배포 
- aws : EC2, RDS (현재는 모두 삭제)

## API

### MemberController
> #### GET 모든멤버조회
> http://localhost:8080/members

> #### GET 로그인한 멤버 정보 조회
> http://localhost:8080/members/member

> #### GET 조건으로 멤버 검색
> http://localhost:8080/members/condition?name=:name&phoneNumber=:phoneNumber

> #### PUT 로그인 중인 회원정보 수정 요청
> http://localhost:8080/members/edit
```
Body
  {
        "name" : "alli",
        "password" : "software9,
        "gender" : "FEMALE",
        "birthday" : "2000-12-30"
  }
```
> #### DELETE 회원탈퇴
> http://localhost:8080/members/delete

> #### GET 인가(로그인 상태인지 확인)
> http://localhost:8080/members/auth

> #### GET 핸드폰 번호 중복 검사
> http://localhost:8080/members/dupCheck/

> #### GET 비밀번호 일치 체크
> http://localhost:8080/members/validate/:password

### LoginController

> #### POST 회원가입
> http://localhost:8080/join
```
body
{
    "name" : "한규정",
    "phoneNumber" : "01030221161",
    "password" : "msp214",
    "gender" : "MALE",
    "birthday" : "1999-06-30"
}
```
> #### POST 로그인
> http://localhost:8080/login
```
body
{
    "phoneNumber": "01059135935",
    "password": "123"
}
```
> #### POST 로그아웃
> http://localhost:8080/logout

### ReviewController
> #### POST 영수증 인식
> http://localhost:8080/reviews/receipt
```
body form data 형식
image 
```
> #### POST 리뷰 생성
> http://localhost:8080/reviews/
```
body form-data 형식
content
ratingPoint
Image
StoreName
address
```

> #### GET 전체 리뷰 조회
> http://localhost:8080/reviews/

> #### GET 특정 리뷰 조회
> http://localhost:8080/reviews/:reviewId

> #### GET 특정 가게 리뷰 조회
> http://localhost:8080/reviews/store/31?page=:page&size=:size&season=:season

> #### GET 현재 로그인한 회원의 리뷰 조회
> http://localhost:8080/reviews/member/

> #### DELETE 특정 리뷰 삭제
> http://localhost:8080/reviews/:reviewId

### ReportController

> #### POST 신고 생성
> http://localhost:8080/reports/
body
```
{
    "reviewId": 5,
    "memberId": 168,
    "type": "OTHER",
    "content": "이상한내용이있어여"
}
```

> #### POST 신고 처리상태 변경
> http://localhost:8080/reports/:reportId

> #### GET 전체 신고 조회
> http://localhost:8080/reports/

> #### GET 특정 신고 조회
> http://localhost:8080/reports/:reportId

> #### GET 특정 유저의 신고 조회
> http://localhost:8080/reports/member/:memberId

> #### GET 처리 상태로 신고 조회
> http://localhost:8080/reports/status/COMPLETED

### StoreController

> #### GET 특정 가게정보 조회
> http://localhost:8080/store/:storeId

> #### GET 조건으로 가게 조회
> http://localhost:8080/stores/condition?address=:address&city=:city&name=:name

> #### GET 지역별 가게 리스트 조회(지도)
> http://localhost:8080/stores/map/:city

> #### GET 지역별 가게 랭킹 페이지 조회
> http://localhost:8080/stores/rank/수?page=:page&size=:size

### WishController

> #### PUT 특정 가게 찜하기 상태 변경
> http://localhost:8080/wish/change/:storeId

> #### GET 현재 회원의 찜하기 목록 조회
> http://localhost:8080/wishes
    
> #### GET 현재 회원의 특정 가게 찜유무 조회
> http://localhost:8080/wish/state/:storeId

### SeasonRankController

> #### GET 특정 가게의 명예의 전당 랭킹 이력 조회
> http://localhost:8080/seasonRank/store/:storeId

> #### GET 지역별 특정 시즌 명예의 전당 리스트 조회
> http://localhost:8080/seasonRank/:season/:city

> #### GET 명예의 전당에 있는 시즌 이름들 조회
> http://localhost:8080/seasonRank/seasonName

> #### GET 현재 시즌 조회
> http://localhost:8080/seasonRank/now

> #### GET 다음 시즌까지의 날짜, 시간 조회
> http://localhost:8080/seasonRank/next

