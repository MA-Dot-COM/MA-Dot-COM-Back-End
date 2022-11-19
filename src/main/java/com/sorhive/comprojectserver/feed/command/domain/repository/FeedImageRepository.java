package com.sorhive.comprojectserver.feed.command.domain.repository;

import com.sorhive.comprojectserver.feed.command.domain.model.feedimage.FeedImage;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : FeedImageRepository
 * Comment: 피드 이미지 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-19       부시연           최초 생성
 * 2022-11-19       부시연           이미지 저장
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface FeedImageRepository extends Repository<FeedImage, Long> {

    void save(FeedImage feedImage);
}
