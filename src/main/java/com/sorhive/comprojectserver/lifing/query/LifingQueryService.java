package com.sorhive.comprojectserver.lifing.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingvisit.LifingVisit;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingVisitRepository;
import com.sorhive.comprojectserver.lifing.query.dto.FindLifingResponseDto;
import com.sorhive.comprojectserver.lifing.query.dto.LifingCommentData;
import com.sorhive.comprojectserver.lifing.query.dto.LifingData;
import com.sorhive.comprojectserver.lifing.query.dto.LifingRequestDto;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : LifingQueryService
 * Comment: 라이핑 조회원 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           회원 번호로 회원의 모든 라이핑 조회
 * 2022-11-12       부시연           회원 번호로 회원의 모든 라이핑 조회 시 조회수 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class LifingQueryService {

    private static final Logger log = LoggerFactory.getLogger(LifingQueryService.class);
    private final LifingMapper lifngMapper;
    private final LifingDataDao lifingDataDao;
    private final LifingRepository lifingRepository;
    private final LifingVisitRepository lifingVisitRepository;
    private final LifingMapper lifingMapper;
    private final TokenProvider tokenProvider;

    /** 회원 번호로 회원의 모든 라이핑 조회*/
    public List<LifingData> findAllLifingByMemberCode(LifingRequestDto lifingRequestDto) {

        log.info("[LifingQueryService] findAllLifingByMemberCode Start ============");

        int pageNo = lifingRequestDto.getPageNo() - 1;

        Long memberCode = lifingRequestDto.getMemberCode();

        List<LifingData> lifingData = lifngMapper.findAllLifingByMemberCode(pageNo, memberCode.intValue());

        return lifingData;
    }

    /** 라이핑 번호로 라이핑 상세 조회 */
    public FindLifingResponseDto findLifingByLifingId(String accessToken, Long lifingId) {

        log.info("[LifingQueryService] findLifingByLifingId Start ===================================");

        /* 토큰에서 멤버 코드 값 꺼내오기 */
        Long visitorCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 반환하기 위한 응답 전송 객체 만들기 */
        FindLifingResponseDto findLifingResponseDto = new FindLifingResponseDto();

        LifingData lifingData = lifingDataDao.findLifingDataByLifingIdAndDeleteYnEquals(lifingId, 'N');

        /* 만약 조회하려는 라이핑 멤버 번호와 라이핑을 방문하는 멤버 번호가 다르다면  */
        if(lifingData.getLifingWriterCode() != visitorCode) {

            Lifing lifing = lifingRepository.findByLifingId(lifingId);

            /* 라이핑 조회 생성 */
            LifingVisit lifingVisit = new LifingVisit(
                    new MemberCode(visitorCode),
                    lifing.getLifingWriter(),
                    lifing
            );

            lifingVisitRepository.save(lifingVisit);

        }

        /* 라이핑에 댓글 있는지 확인 */
        if(lifingMapper.selectAllLifingComments(lifingId) != null) {

            List<LifingCommentData> lifingCommentDataList = lifingMapper.selectAllLifingComments(lifingId);

            findLifingResponseDto.setLifingCommentDataList(lifingCommentDataList);
        }


        findLifingResponseDto.setLifingData(lifingData);

        return findLifingResponseDto;

    }
}
