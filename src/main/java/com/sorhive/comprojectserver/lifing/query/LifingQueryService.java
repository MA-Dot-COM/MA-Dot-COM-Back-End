package com.sorhive.comprojectserver.lifing.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
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

    /** 회원 번호로 회원의 모든 라이핑 조회*/
    public List<LifingData> findAllLifingByMemberCode(LifingRequestDto lifingRequestDto) {

        log.info("[LifingQueryService] findAllLifingByMemberCode Start ============");

        int pageNo = lifingRequestDto.getPageNo() - 1;

        Long memberCode = lifingRequestDto.getMemberCode();

        List<LifingData> lifingData = lifngMapper.findAllLifingByMemberCode(pageNo, memberCode.intValue());

        return lifingData;
    }

    /** 라이핑 번호로 라이핑 상세 조회 */
    public LifingData findLifingByLifingId(Long lifingId) {

        log.info("[LifingQueryService] findLifingByLifingId Start ===================================");

        LifingData lifingData = lifingDataDao.findLifingDataByLifingIdAndDeleteYnEquals(lifingId, 'N');

        return lifingData;

    }
}
