package com.sorhive.comprojectserver.room.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.room.command.application.dto.GuestBookCreateRequestDto;
import com.sorhive.comprojectserver.room.command.application.dto.GuestBookCreateResponseDto;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBookWriter;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBookWriterService;
import com.sorhive.comprojectserver.room.command.domain.repository.GuestBookRepository;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomRepository;
import com.sorhive.comprojectserver.room.command.domain.room.Room;
import com.sorhive.comprojectserver.room.query.NoRoomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : RoomService
 * Comment: 방 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * 2022-11-13       부시연           방명록 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final RoomRepository roomRepository;
    private final GuestBookRepository guestBookRepository;
    private final GuestBookWriterService guestBookWriterService;
    private final TokenProvider tokenProvider;


    public RoomService(RoomRepository roomRepository, GuestBookRepository guestBookRepository, GuestBookWriterService guestBookWriterService, TokenProvider tokenProvider) {
        this.roomRepository = roomRepository;
        this.guestBookRepository = guestBookRepository;
        this.guestBookWriterService = guestBookWriterService;
        this.tokenProvider = tokenProvider;
    }

    /** 방명록 생성 */
    public GuestBookCreateResponseDto createGuestBook(String accessToken, GuestBookCreateRequestDto guestBookCreateRequestDto) {

        log.info("[RoomService] createRoom Start ===================================");
        log.info("[RoomService] roomCreateDto {}", guestBookCreateRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 방명록 작성자 생성 */
        GuestBookWriter guestBookWriter = guestBookWriterService.createGuestBookWriter(new MemberCode(memberCode));

        /* 요청 객체에서 값 꺼내오기 */
        String guestBookContent = guestBookCreateRequestDto.getContent();
        Long roomId = guestBookCreateRequestDto.getRoomId();

        /* 방번호에 맞는 방 찾기 */
        Room room = roomRepository.findById(roomId);

        if(room == null) {
            throw new NoRoomException("해당 방은 존재하지 않습니다.");
        }

        /* 방명록 생성하기 */
        GuestBook guestBook = new GuestBook(
                guestBookContent,
                guestBookWriter,
                room
        );

        /* 방명록 저장하기 */
        guestBookRepository.save(guestBook);

        /* 방명록 응답 전송 객체 생성 */
        GuestBookCreateResponseDto guestBookCreateResponseDto = new GuestBookCreateResponseDto();

        /* 응답 객체에 정보 넣기 */
        guestBookCreateResponseDto.setGuestBookId(guestBook.getId());
        guestBookCreateResponseDto.setGuestBookContent(guestBook.getContent());
        guestBookCreateResponseDto.setCreateTime(guestBook.getCreateTime());
        guestBookCreateResponseDto.setGuestBookWriterCode(guestBook.getGuestBookWriter().getMemberCode().getValue());
        guestBookCreateResponseDto.setGuestBookWriterName(guestBook.getGuestBookWriter().getName());
        guestBookCreateResponseDto.setGuestBookWriterId(guestBook.getGuestBookWriter().getId());
        guestBookCreateResponseDto.setRoomId(guestBook.getRoom().getId());

        log.info("[RoomService] guestBookCreateResponseDto {}", guestBookCreateResponseDto);
        log.info("[RoomService] createRoom End ===================================");

        return guestBookCreateResponseDto;
    }

}
