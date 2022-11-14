package com.sorhive.comprojectserver.lifing.command.application.service;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingDto;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.command.infra.NoLifingNoException;
import com.sorhive.comprojectserver.lifing.query.LifingImagePath;
import com.sorhive.comprojectserver.lifing.query.LifingMapper;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <pre>
 * Class : LifingService
 * Comment: 라이핑 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           라이핑 저장
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class LifingService {

    private static final Logger log = LoggerFactory.getLogger(LifingService.class);
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final LifingMapper lifingMapper;
    private final MemberRepository memberRepository;

    public LifingService(LifingRepository lifingRepository, LifingWriterService lifingWriterService, TokenProvider tokenProvider, LifingMapper lifingMapper, MemberRepository memberRepository) {

        this.lifingRepository = lifingRepository;
        this.lifingWriterService = lifingWriterService;
        this.tokenProvider = tokenProvider;
        this.lifingMapper = lifingMapper;
        this.memberRepository = memberRepository;

    }


    /** 라이핑 생성하기 */
    public ResponseLifingDto createLifing(String accessToken, LifingCreateDto lifingCreateDto) {

        log.info("[LifingService] createLifing Start =====================");
        log.info("[LifingService] lifingCreateDto " + lifingCreateDto);

        /* 토큰에서 멤버 코드 값 꺼내오기 */
        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 라이핑 내용 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingContent() == null) {
            throw new NoContentException("라이핑에 내용이 없습니다.");
        }

        /* 라이핑 번호가 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingNo() == null) {
            throw new NoLifingNoException("라이핑 번호가 없습니다.");
        }

        /* 라이핑 카테고리 번호가 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingCategoryNo() == null) {
            throw new NoLifingNoException("라이핑 카테고리 번호가 없습니다.");
        }

        /* 라이핑 작성자 만들기 */
        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        Long lifingNo = lifingCreateDto.getLifingNo();
        Long lifingCategoryNo = lifingCreateDto.getLifingCategoryNo();
        String lifingConetent = lifingCreateDto.getLifingContent();

        LifingImagePath lifingImagePath = lifingMapper.findLifingImageByMemberCode(memberCode);

        /* 라이핑 생성하기 */
        Lifing lifing = new Lifing(
                lifingWriter,
                lifingNo,
                lifingCategoryNo,
                lifingConetent,
                lifingImagePath.getLifingPath()
        );

        /* 라이핑 저장하기 */
        lifingRepository.save(lifing);

        /* 라이핑 번호를 저장하기 위해 멤버 데이터 조회하기 */
        Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
        Member member = memberData.get();
        member.setLifingNo(lifingNo, lifingCategoryNo);

        /* 멤버에 라이핑번호 정보 저장하기 */
        memberRepository.save(member);

        /* 라이핑 생성 반환 전송 객체 만들기 */
        ResponseLifingDto responseLifingDto = new ResponseLifingDto();

        responseLifingDto.setLifingId(lifing.getLifingId());
        responseLifingDto.setLifingContent(lifing.getLifingConetent());
        responseLifingDto.setLifingImagePath(lifing.getLifingImagePath());
        responseLifingDto.setLifingNo(lifing.getLifingNo());
        responseLifingDto.setLifingCategoryNo(lifing.getLifingCategoryNo());
        responseLifingDto.setLifingCreateTime(lifing.getCreateTime());
        responseLifingDto.setLifingWriter(lifing.getLifingWriter());

        return responseLifingDto;
    }
}
