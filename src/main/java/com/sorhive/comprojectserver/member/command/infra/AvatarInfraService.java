package com.sorhive.comprojectserver.member.command.infra;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.file.S3MemberFile;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarCreateDto;
import com.sorhive.comprojectserver.member.command.domain.model.avatar.Avatar;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarRepository;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

/**
 * <pre>
 * Class : AvatarInfraService
 * Comment: 아바타 인프라 서비스(외부 서버와 연동 : 파일서버)
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * 2022-11-11       부시연           바이트배열로 대응
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class AvatarInfraService {

    private static final Logger log = LoggerFactory.getLogger(AvatarInfraService.class);
    private final AvatarRepository avatarRepository;
    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;
    private S3MemberFile s3MemberFile;

    public AvatarInfraService(AvatarRepository avatarRepository, TokenProvider tokenProvider, MemberRepository memberRepository, S3MemberFile s3MemberFile) {
        this.avatarRepository = avatarRepository;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.s3MemberFile = s3MemberFile;
    }

    /** 아바타 생성하기 */
    @Transactional
    public Long createAvatar(String accessToken, AvatarCreateDto avatarCreateDto) {

        log.info("[AvatarService] createAvatar Start ===============");
        log.info("[AvatarService] avatarCreateDto : " + avatarCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        String memberAvatarImageName = "avatar_" + memberCode + ".png";

        if(avatarCreateDto.getMemberAvatarImage() == null) {
            throw new NoContentException("아바타 이미지가 없습니다.");
        }

        byte[] avatarImage = avatarCreateDto.getMemberAvatarImage();

        try {
            if(avatarImage != null) {

                Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
                Member member = memberData.get();
                member.setAvatarImagePath(s3MemberFile.upload(avatarImage, "images", memberAvatarImageName));
                memberRepository.save(member);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Avatar avatar = new Avatar(
                memberCode,
                avatarCreateDto.getFaceType(),
                avatarCreateDto.getEyeType(),
                avatarCreateDto.getEyeBrowsType(),
                avatarCreateDto.getHairType()
        );

        avatarRepository.save(avatar);

        return memberCode;
    }
}
