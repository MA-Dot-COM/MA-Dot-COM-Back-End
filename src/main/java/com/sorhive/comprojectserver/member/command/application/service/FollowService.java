package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.NoMemberException;
import com.sorhive.comprojectserver.member.command.domain.model.follow.Follow;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowerMember;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowingMember;
import com.sorhive.comprojectserver.member.command.domain.repository.FollowRepository;
import com.sorhive.comprojectserver.member.query.MemberDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class : FollowService
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
@Service
public class FollowService {

    private static final Logger log = LoggerFactory.getLogger(FollowService.class);
    private final FollowRepository followRepository;
    private final MemberDataDao memberDataDao;

    private final TokenProvider tokenProvider;

    public FollowService(FollowRepository followRepository, MemberDataDao memberDataDao, TokenProvider tokenProvider) {
        this.followRepository = followRepository;
        this.memberDataDao = memberDataDao;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public Object createFollow(String accessToken, String followId) {
        log.info("[AvatarService] insertImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + followId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(memberDataDao.findByMemberCode(Long.valueOf(followId)) != null) {

            Follow follow = new Follow(
                    new FollowerMember(memberCode),
                    new FollowingMember(Long.valueOf(followId))
            );

            followRepository.save(follow);

            return follow.getId();

        } else {
            throw new NoMemberException("해당 회원이 존재하지 않습니다.");
        }

    }
}
