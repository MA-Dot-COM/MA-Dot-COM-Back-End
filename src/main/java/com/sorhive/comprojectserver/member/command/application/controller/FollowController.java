package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.NoFollowException;
import com.sorhive.comprojectserver.member.command.application.NoMemberException;
import com.sorhive.comprojectserver.member.command.application.service.FollowService;
import com.sorhive.comprojectserver.member.command.domain.repository.FollowRepository;
import com.sorhive.comprojectserver.member.query.MemberDataDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : FollowController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class FollowController {

    private FollowService followService;
    private final TokenProvider tokenProvider;
    private final MemberDataDao memberDataDao;
    private final FollowRepository followRepository;

    public FollowController(FollowService followService, TokenProvider tokenProvider, MemberDataDao memberDataDao, FollowRepository followRepository) {
        this.followService = followService;
        this.tokenProvider = tokenProvider;
        this.memberDataDao = memberDataDao;
        this.followRepository = followRepository;
    }

    @PostMapping("follow/{followId}")
    public ResponseEntity<ResponseDto> createFollow(@RequestHeader String Authorization, @PathVariable Long followId ) {

        String accessToken = Authorization.substring(7);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(memberDataDao.findByMemberCode(followId) == null) {
            throw new NoMemberException("해당 회원이 존재하지 않습니다.");
        }

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로우 추가 성공", followService.createFollow(memberCode, followId)));

    }

    @DeleteMapping("follow/{followId}")
    public ResponseEntity<ResponseDto> deleteFollow(@PathVariable Long followId ) {

        if(followRepository.findById(followId) != null) {
            throw new NoFollowException("해당 팔로우 번호가 존재하지 않습니다.");
        }

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로우 삭제 성공", followService.deleteFollow(followId)));

    }
}
