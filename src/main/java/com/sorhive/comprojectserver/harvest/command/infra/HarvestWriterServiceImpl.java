package com.sorhive.comprojectserver.harvest.command.infra;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriter;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriterService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : HarvestWriterServiceImpl
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class HarvestWriterServiceImpl implements HarvestWriterService {

    private MemberQueryService memberQueryService;

    @Override
    public HarvestWriter createHarvestWriter(MemberCode harvestWrtierMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(harvestWrtierMemberCode.getValue());

        return new HarvestWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName());
    }
}
