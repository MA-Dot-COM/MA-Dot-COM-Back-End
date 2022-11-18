package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.common.exception.NotSameWriterException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.file.S3LifingImageFile;
import com.sorhive.comprojectserver.lifing.command.application.dto.*;
import com.sorhive.comprojectserver.lifing.command.application.exception.NoLifingException;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingComment;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingCommentRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingImageRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
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
import java.util.*;

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
 * 2022-11-16       부시연           라이핑 이미지 리스트로 변경
 * 2022-11-17       부시연           라이핑 삭제 기능 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class LifingInfraService {

    private static final Logger log = LoggerFactory.getLogger(LifingInfraService.class);
    private final S3LifingImageFile s3LifingImageFile;
    private final LifingImageRepository lifingImageRepository;
    private final LifingRepository lifingRepository;
    private final LifingCommentRepository lifingCommentRepository;
    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    @Value("${url.lifing}")
    private final String url;
    public LifingInfraService(S3LifingImageFile s3LifingImageFile, LifingImageRepository lifingImageRepository, LifingRepository lifingRepository, LifingCommentRepository lifingCommentRepository, LifingWriterService lifingWriterService, TokenProvider tokenProvider, MemberRepository memberRepository, String url) {
        this.s3LifingImageFile = s3LifingImageFile;
        this.lifingImageRepository = lifingImageRepository;
        this.lifingRepository = lifingRepository;
        this.lifingCommentRepository = lifingCommentRepository;
        this.lifingWriterService = lifingWriterService;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.url = url;
    }

    /** 라이핑 AI 이미지 저장 */
    @Transactional
    public ResponseLifingImageAiDto insertLifingAiImage(String accessToken, LifingAIImageDto lifingAIImageDto) {

        log.info("[LifingImageService] insertLifingImage Start ===============");
        log.info("[LifingImageService] lifingImageDto : " + lifingAIImageDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 라이핑 작성자 생성하기(미리 라이핑 작성자를 생성해서 라이핑 번호를 만든다.) */
        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        /* 라이핑 생성 */
        Lifing lifing = new Lifing(
                lifingWriter
        );

        lifingRepository.save(lifing);

        ResponseLifingImageAiDto responseLifingImageAiDto = new ResponseLifingImageAiDto();

        responseLifingImageAiDto.setLifingId(lifing.getLifingId());

        try {

            /* 라이핑 이미지가 존재 한다면 */
            if (lifingAIImageDto.getLifingImages() != null) {

                List<String> lifingImagePathList = new ArrayList<>();

                /* 이미지들 하나씩 꺼내오기 */
                for (int i = 0; i < lifingAIImageDto.getLifingImages().size(); i++) {

                    byte[] lifingByteImage = lifingAIImageDto.getLifingImages().get(i).getLifingImage();
                    String originalName = lifingAIImageDto.getLifingImages().get(i).getLifingImageName();
                    String changeName = UUID.randomUUID() + " - lifing_" + memberCode + ".png";

                    /* 라이핑 이미지를 S3에 저장하고 URL 값을 반환 받는다. */
                    String lifingImagePath = s3LifingImageFile.upload(lifingByteImage, changeName, "images");

                    /* 라이핑 이미지 생성 */
                    LifingImage lifingImage = new LifingImage(

                            lifingImagePath,
                            originalName,
                            changeName,
                            lifing

                    );

                    lifingImageRepository.save(lifingImage);
                    
                    /* 라이핑 이미지 경로 저장 */
                    lifingImagePathList.add(lifingImagePath);

                }

                RequestLifingImageAiDto requestLifingImageAiDto = new RequestLifingImageAiDto();

                requestLifingImageAiDto.setImgNum(lifingImagePathList.size());
                requestLifingImageAiDto.setUrl(lifingImagePathList);

                /* AI서버에 전송하기 위해 차셋 등 헤더 설정하기 */
                HttpHeaders headers = new HttpHeaders();
                Charset utf8 = Charset.forName("UTF-8");
                MediaType mediaType = new MediaType("application", "json", utf8);
                headers.setContentType(mediaType);

                /* 바디에 넣기 위한 값을 MAP에 넣어주기 */
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < requestLifingImageAiDto.getUrl().size(); i++) {
                    map.put("url", requestLifingImageAiDto.getUrl().get(i));
                }

                /* JSON으로 파싱하기 */
                JSONObject params = new JSONObject(map);

                log.info("params :  " + params);

                /* 헤더와 바디를 하나의 JSON으로 만들기 */
                HttpEntity<JSONObject> requestEntity = new HttpEntity<>(params, headers);

                /* 스프링 3.0부터 지원하는 Spring의 HTTP 통신 템플릿 */
                RestTemplate restTemplate = new RestTemplate();

                /* 응답받기 위한 값 설정하기  */
                ResponseEntity<ResponseLifingImageAiDto> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ResponseLifingImageAiDto.class);

                /* 분석된 결과값 Long에 담기 */
                Long analyzedLifingNo = res.getBody().getLifingCategoryNo();

                /* 라이핑 이미지에 값 넣어주기 */
                Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifing.getLifingId(), 'N');
                lifing = lifingData.get();
                lifing.createAnalyzedLifingNo(analyzedLifingNo);

                /* 라이핑 이미지 저장하기 */
                lifingRepository.save(lifing);

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
        if(lifingImageDto.getLifingImages() == null) {
            throw new NoContentException("라이핑에 이미지가 없습니다.");
        }

        /* 라이핑 생성 반환 전송 객체 만들기 */
        ResponseLifingDto responseLifingDto = new ResponseLifingDto();

        try {
            /* 라이핑 이미지가 존재 한다면 */
            if (lifingImageDto.getLifingImages() != null) {

                String lifingConetent = lifingImageDto.getLifingContent();

                /* 라이핑 작성자 생성 */
                LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

                /* 라이핑 생성하기 */
                Lifing lifing = new Lifing(
                        lifingWriter,
                        lifingConetent
                );

                /* 라이핑 저장하기 */
                lifingRepository.save(lifing);

                List<String> lifingImagePathList = new ArrayList<>();

                /* 이미지들 하나씩 꺼내오기 */
                for (int i = 0; i < lifingImageDto.getLifingImages().size(); i++) {

                    byte[] lifingByteImage = lifingImageDto.getLifingImages().get(i).getLifingImage();
                    String originalName = lifingImageDto.getLifingImages().get(i).getLifingImageName();
                    String changeName = UUID.randomUUID() + " - lifing_" + memberCode + ".png";

                    /* 라이핑 이미지를 S3에 저장하고 URL 값을 반환 받는다. */
                    String lifingImagePath = s3LifingImageFile.upload(lifingByteImage, changeName, "images");

                    /* 라이핑 이미지 생성 */
                    LifingImage lifingImage = new LifingImage(
                            lifingImagePath,
                            originalName,
                            changeName,
                            lifing
                    );

                    lifingImageRepository.save(lifingImage);

                    /* 라이핑 이미지 경로 저장 */
                    lifingImagePathList.add(lifingImagePath);

                }

                /* 라이핑 번호를 저장하기 위해 멤버 데이터 조회하기 */
                Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
                Member member = memberData.get();
                member.changeLifingWithoutAI();

                /* 멤버에 라이핑번호 정보 저장하기 */
                memberRepository.save(member);

                responseLifingDto.setLifingId(lifing.getLifingId());
                responseLifingDto.setLifingContent(lifing.getLifingConetent());
                responseLifingDto.setLifingNo(lifing.getLifingNo());
                responseLifingDto.setLifingCategoryNo(lifing.getLifingCategoryNo());
                responseLifingDto.setLifingCreateTime(lifing.getCreateTime());
                responseLifingDto.setLifingWriter(lifing.getLifingWriter());
                responseLifingDto.setLifingImagePath(lifingImagePathList);

                return responseLifingDto;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseLifingDto;
    }

    /** 라이핑 삭제 */
    @Transactional
    public Object deleteLifing(String accessToken, Long lifingId) {

        log.info("[LifingService] deleteLifing Start =========================================================");
        log.info("[LifingService] lifingId : " + lifingId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(lifingRepository.findByLifingId(lifingId) == null ) {
            throw new NoLifingException("해당 라이핑은 존재 하지 않습니다");
        }

        if(!lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'Y').isEmpty()) {
            throw new NoLifingException("해당 라이핑은 이미 삭제가 되었습니다.");
        }

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'N');
        Lifing lifing = lifingData.get() ;

        if(lifing.getLifingWriter().getMemberCode().getValue() != memberCode) {
            throw new NotSameWriterException("해당 라이핑 작성자와 요청자가 다릅니다.");
        }

        lifing.delete('Y');
        lifingRepository.save(lifing);

        /* 해당 라이핑에 대한 댓글이 있다면 */
        if(!lifingCommentRepository.findByLifing(lifing).isEmpty()) {

            List<LifingComment> lifingComments = lifingCommentRepository.findByLifing(lifing);
            for (int i = 0; i < lifingComments.size(); i++) {

                /* 하나씩 꺼내서 삭제 여부를 Y로 바꾸어준다. */
                lifingComments.get(i).deleteComment('Y');
                lifingCommentRepository.save(lifingComments.get(i));

            }

        }

        /* 해당 라이핑에 대한 이미지가 있다면 */
        if(!lifingImageRepository.findByLifing(lifing).isEmpty()) {

            List<LifingImage> lifingImages = lifingImageRepository.findByLifing(lifing);
            for (int i = 0; i < lifingImages.size(); i++) {

                /* 하나씩 꺼내서 삭제 여부를 Y로 바꾸어준다. */
                lifingImages.get(i).deleteImage('Y');
                lifingImageRepository.save(lifingImages.get(i));

            }

        }


        return lifing.getLifingId();

    }

}
