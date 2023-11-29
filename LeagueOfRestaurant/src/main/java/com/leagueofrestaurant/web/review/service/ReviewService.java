package com.leagueofrestaurant.web.review.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leagueofrestaurant.api.ocr.OcrService;
import com.leagueofrestaurant.web.common.CommonService;
import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.review.domain.Review;
import com.leagueofrestaurant.web.review.dto.ReceiptInfo;
import com.leagueofrestaurant.web.review.dto.ReviewContent;
import com.leagueofrestaurant.web.review.repository.ReviewRepository;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.store.repository.StoreRepository;
import com.leagueofrestaurant.web.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CommonService commonService;
    private final StoreService storeService;
    private final OcrService ocrService;
    private final ObjectMapper objectMapper;

    // 모든 리뷰 조회
    public List<ReviewContent> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(review -> new ReviewContent(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    // 특정 리뷰 조회
    public ReviewContent getReview(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            return new ReviewContent(review.getContent(), review.getImg(), review.getSeason());
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
    }

    //특정 회원의 리뷰 조희
    public List<ReviewContent> getReviewsByMemberId(Long memberId) {
        List<Review> reviews = reviewRepository.findAllByMemberId(memberId);

        return reviews.stream()
                .map(review -> new ReviewContent(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    // 특정 가게의 리뷰 조회
    public List<ReviewContent> getReviewsByStoreId(Long storeId) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);

        return reviews.stream()
                .map(review -> new ReviewContent(review.getContent(), review.getImg(), review.getSeason()))
                .collect(Collectors.toList());
    }

    //영수증 인증
    public ReceiptInfo getReceiptInfo(MultipartFile image) throws IOException {
        //receiptImg = "/Users/hangyujeong/Desktop/영수증테스트/가은.png"; //테스트용
        String response = ocrService.getReceiptJSON(image);
        JsonNode jsonNode = objectMapper.readTree(response);

//        File jsonFile = new File("/Users/hangyujeong/Desktop/영수증테스트/tomtom.json");
//        // ObjectMapper를 사용하여 JSON 파일을 JsonNode로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(jsonFile);

        // ocrService.getReceiptInfo 메서드에 전달
        List<String> receiptData = ocrService.getReceiptInfo(jsonNode);


        String message = receiptData.get(0); //성공 여부

        if (!"SUCCESS".equals(message)) { //실패
            throw new LORException(ErrorCode.RECEIPT_ERROR);
        }
        else{ //성공
            String storeName = receiptData.get(1);
            String storeAddress = receiptData.get(2);
            return new ReceiptInfo(storeName, storeAddress);
        }
    }

    //리뷰 작성
    public void createReview(long memberId, ReviewContent reviewContent, ReceiptInfo receiptInfo) {
        //member가 존재하지 않는 경우 예외 처리
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new LORException(ErrorCode.NOT_EXIST_MEMBER));
        //영수증 정보에서 가게명, 주소 추출
        //String storeName = receiptInfo.getStoreName();
        //Address address = receiptInfo.getAddress();

        //가게명과 주소로 store를 찾기
//        StoreSearchCondition storeSearchCondition = new StoreSearchCondition(storeName, address);
//        List<StoreDto> store = storeService.getStoreListByCondition(storeSearchCondition);

        //if: store가 존재하지 않을 경우,
        // - 가게 생성을 위한 storeservice의 createStore를 호출
        // - 생성한 가게 자체를 targetStore에 할당
        //else: 존재하는 경우,
        // - 존재하는 가게 자체를 targetStore에 할당

        String season = commonService.getSeason();

        //if: 이미 현재 season에 targetStore에 작성한 리뷰가 있는 경우... exception 처리
        String content = reviewContent.getContent();
        String img = reviewContent.getImg();


        //리뷰 객체 생성
        //Review review = new Review(member, targetStore, content, season, img);

        //데이터베이스에 리뷰 저장
        //reviewRepository.save(review);
    }

    //리뷰 삭제
    public void deleteReview(long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new LORException(ErrorCode.NOT_EXIST_REVIEW);
        }
    }
}
