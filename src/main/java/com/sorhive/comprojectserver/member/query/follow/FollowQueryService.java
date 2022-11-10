package com.sorhive.comprojectserver.member.query.follow;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : IdPasswordNotMatchingException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class FollowQueryService {

    private final FollowDataDao followDataDao;
    private final TokenProvider tokenProvider;

    public FollowQueryService(FollowDataDao followDataDao, TokenProvider tokenProvider) {
        this.followDataDao = followDataDao;
        this.tokenProvider = tokenProvider;
    }

    public Object findFollowerList(String accessToken) {

        Long followerId = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<FollowData> followData = followDataDao.findByFollowerId(followerId);

        return followData;
    }

    public Object findFollowingList(String accessToken) {

        Long followingId = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<FollowData> followData = followDataDao.findByFollowingId(followingId);

        return followData;
    }
}
