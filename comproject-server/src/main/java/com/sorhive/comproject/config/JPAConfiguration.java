package com.sorhive.comproject.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <pre>
 * Class : JPAConfiguration
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-18       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Configuration
@EntityScan(basePackages = {"com.sorhive.comproject"})
@EnableJpaRepositories(basePackages = "com.sorhive.comproject")
public class JPAConfiguration {
}
