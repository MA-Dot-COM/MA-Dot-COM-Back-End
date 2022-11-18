package com.sorhive.comprojectserver.member.command.infra;

import com.sorhive.comprojectserver.member.command.application.dto.EmailRequestDto;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import com.sorhive.comprojectserver.member.command.exception.AlreadyExistEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * <pre>
 * Class : AuthInfraService
 * Comment: 인증 인프라 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-17       부시연           최초 생성
 * 2022-11-17       부시연           이메일 인증
 * 2022-11-18       부시연           인증 코드 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class AuthInfraService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private static final Logger log = LoggerFactory.getLogger(AuthInfraService.class);
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthInfraService(MemberRepository memberRepository, JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }


    /** 이메일 중복 검사 */
    public String emailAuthentication(EmailRequestDto emailRequestDto) throws MessagingException {

        log.info("[AuthInfraService] emailAuthentication Start ============================");
        log.info("[AuthInfraService] " + emailRequestDto);

        int countEmail = memberRepository.countByMemberEmail(emailRequestDto.getEmail());

        if(countEmail > 0) {
            throw new AlreadyExistEmailException("해당 이메일은 이미 존재합니다.");
        }

        String certCode = emailSend(emailRequestDto, certCode());

        log.info("[AuthInfraService] emailAuthentication End ============================");
        
        return certCode;

    }

    /** 인증번호 생성 */
    public String certCode() {

        log.info("[AuthInfraService] certCode Start ============================");

        Random random = new Random();
        int createNumber = 0;
        String tempNumber = "";
        String certCode = "";

        for (int i = 0; i < 6; i++) {

            createNumber = random.nextInt(9);
            tempNumber = Integer.toString(createNumber);
            certCode += tempNumber;

        }

        log.info("[AuthInfraService] certCode End ============================");

        return certCode;
    }

    /** 이메일 보내기 */
    public String emailSend(EmailRequestDto emailRequestDto, String certCode) throws MessagingException {

        log.info("[AuthInfraService] emailSend Start ============================");

        String setFrom = fromEmail;
        String toEmail = emailRequestDto.getEmail();
        String title = "SORHIVE 회원가입 인증 번호";

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setContext(certCode), "utf-8", "html");

        javaMailSender.send(message);

        log.info("[AuthInfraService] emailSend End ============================");

        return certCode;

    }

    /** 타임리프를 이용한 context 설정 */
    public String setContext(String code) {

        log.info("[AuthInfraService] setContext Start ============================");

        Context context = new Context();
        context.setVariable("code", code);

        log.info("[AuthInfraService] setContext End ============================");

        return springTemplateEngine.process("mail", context); //mail.html
    }
}
