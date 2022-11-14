package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.file.S3LifingImageFile;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingAIImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingImageAiDto;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingImageRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.query.LifingMapper;
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
 * Class : LifingInfraService
 * Comment: 라이핑 인프라 서비스(AI, S3 통신)
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           라이핑 이미지 생성 추가
 * 2022-11-14       부시연           라이핑 AI 분석 없는 이미지 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@RequiredArgsConstructor
public class LifingInfraService {

    private static final Logger log = LoggerFactory.getLogger(LifingInfraService.class);
    private final S3LifingImageFile s3LifingImageFile;
    private final LifingImageRepository lifingImageRepository;
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final LifingMapper lifingMapper;
    private final MemberRepository memberRepository;

    @Value("${url.lifing}")
    private String url;

    /** 라이핑 AI 이미지 저장 */
    @Transactional
    public ResponseLifingImageAiDto insertLifingAiImage(String accessToken, LifingAIImageDto lifingAIImageDto) {

        log.info("[LifingImageService] insertLifingImage Start ===============");
        log.info("[LifingImageService] lifingImageDto : " + lifingAIImageDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        String changeName = lifingAIImageDto.getLifingImageName() + "lifing_" + memberCode + ".png";

        try {

            /* 라이핑 이미지가 존재 한다면 */
            if (lifingAIImageDto.getLifingImage() != null) {

                /* 라이핑 이미지를 S3에 저장하고 URL 값을 반환 받는다. */
                String lifingPath = s3LifingImageFile.upload(lifingAIImageDto.getLifingImage(), changeName, "images");

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
                        lifingAIImageDto.getLifingImageName(),
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

    /** AI 분석 없이 라이핑 생성하기 */
    public ResponseLifingDto insertLifingImage(String accessToken, LifingImageDto lifingImageDto) {

        log.info("[LifingInfraService] insertLifingImage Start ===================");
        log.info("[LifingInfraService] lifingImageDto " + lifingImageDto);

        /* 토큰에서 멤버 코드 값 꺼내오기 */
        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 라이핑 이미지가 없을 경우 예외처리 */
        if(lifingImageDto.getLifingImage() == null) {
            throw new NoContentException("라이핑에 이미지가 없습니다.");
        }

        try {

            String changeName = lifingImageDto.getLifingImageName() + "lifing_" + memberCode + ".png";

            /* 라이핑 이미지를 S3에 저장하고 URL 값을 반환 받는다. */
            String lifingPath = s3LifingImageFile.upload(lifingImageDto.getLifingImage(), changeName, "images");

            /* 라이핑 작성자 생성 */
            LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

            Long lifingNo = -1L;
            Long lifingCategoryNo = -1L;

            /* 라이핑 이미지에 값 넣어주기 */
            LifingImage lifingImage = new LifingImage(
                    lifingPath,
                    lifingImageDto.getLifingImageName(),
                    changeName,
                    lifingNo,
                    lifingWriter
            );

            /* 라이핑 이미지 저장하기 */
            lifingImageRepository.save(lifingImage);

            String lifingConetent = lifingImageDto.getLifingContent();

            /* 라이핑 생성하기 */
            Lifing lifing = new Lifing(
                    lifingWriter,
                    lifingNo,
                    lifingCategoryNo,
                    lifingConetent,
                    lifingPath
            );

            /* 라이핑 저장하기 */
            lifingRepository.save(lifing);

            log.info("[LifingImageService] insertLifingImage End ===============");

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
            responseLifingDto.setLifingCreateTime(lifing.getCreateTime());
            responseLifingDto.setLifingWriter(lifing.getLifingWriter());

            return responseLifingDto;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
