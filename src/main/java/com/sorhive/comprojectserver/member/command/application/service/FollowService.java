package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.domain.model.follow.Follow;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowerId;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowingId;
import com.sorhive.comprojectserver.member.command.domain.repository.FollowRepository;
import com.sorhive.comprojectserver.member.query.MemberDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
 * @version 1(클래스 버전)\
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
    public Object createFollow(Long followerId, Long followId) {
        log.info("[FollowService] createFollow Start ===============");
        log.info("[FollowService] followId : " + followId);

        Follow follow = new Follow(
                new FollowerId(followerId),
                new FollowingId(followId)
        );

        followRepository.save(follow);

        return follow.getId();

    }

    @Transactional
    public Object deleteFollow(Long followId) {
        log.info("[FollowService] deleteFollow Start ===============");
        log.info("[FollowService] followId : " + followId);

        Optional<Follow> followingData = followRepository.findById(followId);
        Follow follow = followingData.get();
        follow.setDeleteYn('Y');
        followRepository.save(follow);

        return follow.getId();

    }

}
