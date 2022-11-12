package com.sorhive.comprojectserver.lifing.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.room.query.QueryRoomService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : QueryLifingService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class QueryLifingService {

    private static final Logger log = LoggerFactory.getLogger(QueryLifingService.class);
    private final LifingDataDao lifingDataDao;
    private final TokenProvider tokenProvider;
    public Object findAllLifingByMemberCode(String accessToken) {
        log.info("[QueryLifingService] findAllLifingByMemberCode Start ============================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        return lifingDataDao.findAllLifingByMemberCodeOrderByUploadTimeDesc(memberCode);
    }
}
