package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.file.S3LifingImageFile;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingImageAiDto;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingImageRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.query.LifingImageMapper;
import com.sorhive.comprojectserver.lifing.query.LifingImagePath;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 * Class : LifingImageService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           라이핑 이미지 생성 추가
 * 2022-11-12       부시연           라이핑 저장
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@RequiredArgsConstructor
public class LifingService {

    private static final Logger log = LoggerFactory.getLogger(LifingService.class);
    private final S3LifingImageFile s3LifingImageFile;
    private final LifingImageRepository lifingImageRepository;
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final LifingImageMapper lifingImageMapper;
    private final MemberRepository memberRepository;

    @Value("${url.lifing}")
    private String url;

    /** 라이핑 이미지 저장 */
    @Transactional
    public ResponseLifingImageAiDto insertLifingImage(String accessToken, LifingImageDto lifingImageDto) {

        log.info("[LifingImageService] insertLifingImage Start ===============");
        log.info("[LifingImageService] lifingImageDto : " + lifingImageDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        String changeName = lifingImageDto.getLifingImage() + "lifing_" + memberCode + ".png";

        try {

            /* 라이핑 이미지가 존재 한다면 */
            if (lifingImageDto.getLifingImage() != null) {

                /* 라이핑 이미지를 S3에 저장하고 URL 값을 반환 받는다. */
                String lifingPath = s3LifingImageFile.upload(lifingImageDto.getLifingImage(), changeName, "images");

                /* AI서버에 전송하기 위해 차셋 등 헤더 설정하기 */
                HttpHeaders headers = new HttpHeaders();
                Charset utf8 = Charset.forName("UTF-8");
                MediaType mediaType = new MediaType("application", "json", utf8);
                headers.setContentType(mediaType);

                /* 바디에 넣기 위한 값을 MAP에 넣어주기 */
                Map<String, Object> map = new HashMap<>();
                map.put("url", lifingPath);

                /* JSON으로 파싱하기 */
                JSONObject params = new JSONObject(map);

                log.info("params :  " + params);

                /* 헤더와 바디를 하나의 JSON으로 만들기 */
                HttpEntity<JSONObject> requestEntity = new HttpEntity<>(params, headers);

                /* 스프링 3.0부터 지원하는 Spring의 HTTP 통신 템플릿 */
                RestTemplate restTemplate = new RestTemplate();

                /* 응답받기 위한 값 설정하기  */
                ResponseEntity<ResponseLifingImageAiDto> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ResponseLifingImageAiDto.class);

                /* 라이핑 작성자 생성 */
                LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

                /* 분석된 결과값 Long에 담기 */
                Long analyzedLifingNo = res.getBody().getLifing();

                /* 라이핑 이미지에 값 넣어주기 */
                LifingImage lifingImage = new LifingImage(
                        lifingPath,
                        lifingImageDto.getLifingImageName(),
                        changeName,
                        analyzedLifingNo,
                        lifingWriter
                );
                
                /* 라이핑 이미지 저장하기 */
                lifingImageRepository.save(lifingImage);

                log.info("[LifingImageService] insertImage End ===============");

                return res.getBody();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseLifingImageAiDto();
    }


    /** 라이핑 생성하기 */
    public Object createLifing(String accessToken, LifingCreateDto lifingCreateDto) {

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

        /* 라이핑 작성자 만들기 */
        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        Long lifingNo = lifingCreateDto.getLifingNo();
        String lifingConetent = lifingCreateDto.getLifingContent();

        LifingImagePath lifingImagePath = lifingImageMapper.findLifingImageByMemberCode(memberCode);

        /* 라이핑 생성하기 */
        Lifing lifing = new Lifing(
                lifingWriter,
                lifingNo,
                lifingConetent,
                lifingImagePath.getLifingPath()
        );

        /* 라이핑 저장하기 */
        lifingRepository.save(lifing);

        /* 라이핑 번호를 저장하기 위해 멤버 데이터 조회하기 */
        Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
        Member member = memberData.get();
        member.setLifingNo(lifingNo);

        /* 멤버에 라이핑번호 정보 저장하기 */
        memberRepository.save(member);

        /* 라이핑 생성 반환 전송 객체 만들기 */
        ResponseLifingDto responseLifingDto = new ResponseLifingDto();

        responseLifingDto.setLifingId(lifing.getLifingId());
        responseLifingDto.setLifingContent(lifing.getLifingConetent());
        responseLifingDto.setLifingImagePath(lifing.getLifingImagePath());
        responseLifingDto.setLifingNo(lifing.getLifingNo());
        responseLifingDto.setLifingCreateTime(lifing.getCreateTime());
        responseLifingDto.setLifingWriter(lifing.getLifingWriter());

        return responseLifingDto;
    }
}
