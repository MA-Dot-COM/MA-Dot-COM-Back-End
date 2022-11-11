package com.sorhive.comprojectserver.harvest.query;//package com.sorhive.comprojectserver.harvest.query;
//
//import org.hibernate.annotations.Immutable;
//import org.hibernate.annotations.Subselect;
//
//import javax.persistence.Entity;
//
//
///**
// * <pre>
// * Class : HarvestSummary
// * Comment: 클래스에 대한 간단 설명
// * History
// * ================================================================
// * DATE             AUTHOR           NOTE
// * ----------------------------------------------------------------
// * 2022-11-04       부시연           최초 생성
// * </pre>
// *
// * @author 부시연(최초 작성자)
// * @version 1(클래스 버전)
// * @see (참고할 class 또는 외부 url)
// */
//@Entity
//@Immutable
//@Subselect(
//        """
//        select
//                a.harvest_id
//              , a.harvest_content
//              , a.harvest_create_time
//              , a.harvest_upload_time
//              , b.harvest_image_path
//              , c.harvest_comment_id
//              , c.harvest_comment_content
//              , c.harvest_comment_create_time
//              , c.harvest_comment_upload_time
//              , d.honey_id
//           from tbl_harvest a
//           join a.harvest_id =
//
//        """
//)
//public class HarvestSummary {
//}
