package com.sorhive.comprojectserver.member.query.recommend;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.exception.NoMemberException;
import com.sorhive.comprojectserver.member.query.member.FindAllMemberResponseDto;
import com.sorhive.comprojectserver.member.query.member.MemberMapper;
import com.sorhive.comprojectserver.member.query.member.MemberSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : MongoRecommendQueryService
 * Comment: 회원 추천 조회용 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-25       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class MongoRecommendQueryService {

    private static final Logger log = LoggerFactory.getLogger(MongoRecommendQueryService.class);
    private final TokenProvider tokenProvider;
    private final MongoRecommendQueryRepository mongoRecommendQueryRepository;
    private final RecommendMapper recommendMapper;
    private final MemberMapper memberMapper;

    public MongoRecommendQueryService(TokenProvider tokenProvider, MongoRecommendQueryRepository mongoRecommendQueryRepository, RecommendMapper recommendMapper, MemberMapper memberMapper) {
        this.tokenProvider = tokenProvider;
        this.mongoRecommendQueryRepository = mongoRecommendQueryRepository;
        this.recommendMapper = recommendMapper;
        this.memberMapper = memberMapper;
    }

    public FindAllMemberResponseDto findAllByMemberCode(String accessToken, Long offset) {

        log.info("[MemberQueryService] findAllByMemberCode Start ================");
        log.info("[MemberQueryService] offset : " + offset);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 자신 조회하기 */
        MemberSummary memberSummary = memberMapper.findOneByMemberCode(memberCode);

        List<MemberSummary> memberSummaryList = new ArrayList<>();

        /* 응답 전송 객체 만들기 */
        FindAllMemberResponseDto findAllMemberResponseDto = new FindAllMemberResponseDto();

        if (memberSummary == null) {
            throw new NoMemberException("해당 회원은 존재 하지 않습니다.");
        }

        /* 응답 전송 객체에 담기 위해 리스트에 자신의 정보 넣기 */
        memberSummaryList.add(memberSummary);

        /* 회원 번호의 손님들의 추천리스트 가져오기 */
        List<String> recommendList = findGuestList(memberCode);

        /* 자신이 팔로우 하고 있는 인원들의 목록 */
        List<MemberSummary> tempMemberSummaryList = memberMapper.findAllFollowerByMemberCode(memberCode, offset * 30);

        /* 임시 리스트 2개 선언 및 초기화 */
        List<Integer> tempList = new ArrayList<>();
        List<Integer> tempList2 = new ArrayList<>();

        /* 팔로우 하고 있는 목록이 있다면 */
        if(!tempMemberSummaryList.isEmpty()) {

            for (int i = 0; i < tempMemberSummaryList.size(); i++) {

                /* 만약 추천 리스트에 임시 회원 요약 리스트의 회원 코드가 존재한다면 */
                if (recommendList.contains(tempMemberSummaryList.get(i).getMemberCode().toString())) {

                    /* 임시 리스트에 해당 추천리스트 인덱스 번호를 추가한다. */
                    tempList.add(recommendList.indexOf(tempMemberSummaryList.get(i).getMemberCode().toString()));
                }

            }

            /* 추천 순위에 맞게 정렬하기 */
            Collections.sort(tempList);

            /* 정렬된 값을 통해 추천 리스트의 순서에 맞게 값 넣어주기 */
            for (int i = 0; i < tempList.size(); i++) {
                tempList2.add(Integer.valueOf(recommendList.get(tempList.get(i))));
            }

            System.out.println(tempList2);

            /* 임시 리스트2의 사이즈만큼 반복 */
            for (int i = 0; i < tempList2.size(); i++) {

                /* 임시 회원요약리스트의 사이즈만큼 반복 */
                for (int j = 0; j < tempMemberSummaryList.size(); j++) {

                    /* 만약 임시 리스트2의 값과 임시회원요약리스트의 회원코드가 같다면 */
                    if(tempList2.get(i) == tempMemberSummaryList.get(j).getMemberCode().intValue()) {

                        /* 회원요약리스트에 임시회원요약리스트 추가해주기 */
                        memberSummaryList.add(tempMemberSummaryList.get(j));
                    }
                }

            }

        }

        /* 전송객체에 값을 담아서 반환하기 */
        findAllMemberResponseDto.setMemberSummary(memberSummaryList);

        return findAllMemberResponseDto;
    }

    /** 친구추천 배열 불러오기 */
    public List<String> findGuestList(Long memberCode) {

        log.info("[MongoRecommendQueryService] Start ==============================");

        String recommendId = recommendMapper.findRecommendId();

        Optional<MongoRecommendData> mongoRecommendData = mongoRecommendQueryRepository.findById(recommendId);

        MongoRecommendData mongoRecommend = mongoRecommendData.get();

        List<String> guestCodes = new ArrayList<>();

        for (int i = 0; i < mongoRecommend.getMemberCodes().size(); i++) {

            if(mongoRecommend.getMemberCodes().get(i).equals(memberCode.toString())) {

                guestCodes = mongoRecommend.getGuestCodes().get(i);

                log.info("[MongoRecommendQueryService] End ==============================");

                return guestCodes;
            }
        }

        log.info("[MongoRecommendQueryService] End ==============================");

        return guestCodes;

    }
}
