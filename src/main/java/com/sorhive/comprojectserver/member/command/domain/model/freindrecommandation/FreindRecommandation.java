package com.sorhive.comprojectserver.member.command.domain.model.freindrecommandation;

import javax.persistence.*;

/**
 * <pre>
 * Class : FreindRecommandation
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_freind_recommandations")
public class FreindRecommandation {

    @Id
    @Column(name = "freind_recommandation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
