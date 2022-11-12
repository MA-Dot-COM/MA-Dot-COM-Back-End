package com.sorhive.comprojectserver.lifing.command.domain.repository;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : LifingImageRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface LifingImageRepository extends Repository<LifingImage, Long> {
    void save(LifingImage lifingImage);
    Optional<LifingImage> findById(Long memberCode);
}
