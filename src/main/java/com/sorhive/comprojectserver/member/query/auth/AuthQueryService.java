package com.sorhive.comprojectserver.member.query.auth;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberId;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : AuthQueryService
 * Comment: 인증 조회 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-16       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class AuthQueryService {

    private static final Logger log = LoggerFactory.getLogger(AuthQueryService.class);
    private final MemberRepository memberRepository;

    public AuthQueryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String idCheck(String newMemberId) {

        log.info("[AuthQueryService] idCheck Start =================");
        log.info("[AuthQueryService] newMemberId " + newMemberId);

        String idCheck = "";

        if(memberRepository.findByMemberId(MemberId.of(newMemberId)) == null) {

            idCheck = "중복된 아이디가 없습니다";

        } else {

            idCheck = "중복된 아이디가 존재합니다 !";

        }

        return idCheck;

    }
}
