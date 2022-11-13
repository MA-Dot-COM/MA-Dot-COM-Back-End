package com.sorhive.comprojectserver.lifing.query;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private final LifingDataDao lifingDataDao;

    /** 회원 번호로 회원의 모든 라이핑 조회*/
    public Object findAllLifingByMemberCode(LifingRequestDto lifingRequestDto) {

        log.info("[QueryLifingService] findAllLifingByMemberCode Start ============================");
        log.info("[QueryLifingService] lifingRequestDto" + lifingRequestDto);

        int pageNo = lifingRequestDto.getPageNo() - 1;

        Pageable paging = PageRequest.of(pageNo, 2);

        return lifingDataDao.findAllLifingByMemberCodeContainingOrderByUploadTimeDesc(lifingRequestDto.getMemberCode(), paging);
    }
}
