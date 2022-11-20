package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.dto.MemberModifyRequestDto;
import com.sorhive.comprojectserver.member.command.application.dto.MemberModifyResponseDto;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <pre>
 * Class : MemberService
 * Comment: 회원 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-21       부시연           최초 생성
 * 2022-11-21       부시연           회원 수정 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public MemberService(TokenProvider tokenProvider, MemberRepository memberRepository) {
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
    }

    /** 회원 수정 */
    public MemberModifyResponseDto modifyMember(String accessToken, MemberModifyRequestDto memberModifyRequestDto) {

        log.info("[MemberService] modifyMember Start ===============");
        log.info("[MemberService] memberModifyRequestDto : " + memberModifyRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Optional<Member> memberData =  memberRepository.findByMemberCode(memberCode);

        Member member = memberData.get();

        member.updateMember(memberModifyRequestDto.getName());

        memberRepository.save(member);

        MemberModifyResponseDto memberModifyResponseDto = new MemberModifyResponseDto(
                member.getMemberCode(),
                member.getMemberId(),
                member.getMemberName(),
                member.getUploadTime()
        );

        return memberModifyResponseDto;

    }
}
