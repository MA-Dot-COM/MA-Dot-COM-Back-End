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
 * Comment: 클래스에 대한 간단 설명
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

    public GuestBookCreateResponseDto createGuestBook(String accessToken, GuestBookCreateRequestDto guestBookCreateRequestDto) {

        log.info("[RoomService] createRoom Start ===================================");
        log.info("[RoomService] roomCreateDto {}", guestBookCreateRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        GuestBookWriter guestBookWriter = guestBookWriterService.createGuestBookWriter(new MemberCode(memberCode));

        String guestBookContent = guestBookCreateRequestDto.getContent();
        Long roomId = guestBookCreateRequestDto.getRoomId();

        Room room = roomRepository.findById(roomId);

        if(room == null) {
            throw new NoRoomException("해당 방은 존재하지 않습니다.");
        }

        GuestBook guestBook = new GuestBook(
                guestBookContent,
                guestBookWriter,
                room
        );

        guestBookRepository.save(guestBook);

        GuestBookCreateResponseDto guestBookCreateResponseDto = new GuestBookCreateResponseDto();

        guestBookCreateResponseDto.setGuestBookId(guestBook.getId());
        guestBookCreateResponseDto.setGuestBookContent(guestBook.getContent());
        guestBookCreateResponseDto.setCreateTime(guestBook.getCreateTime());
        guestBookCreateResponseDto.setGuestBookWriterCode(guestBook.getGuestBookWriter().getMemberCode().getValue());
        guestBookCreateResponseDto.setGuestBookWriterName(guestBook.getGuestBookWriter().getName());
        guestBookCreateResponseDto.setRoomId(guestBook.getRoom().getId());

        log.info("[RoomService] guestBookCreateResponseDto {}", guestBookCreateResponseDto);
        log.info("[RoomService] createRoom End ===================================");

        return guestBookCreateResponseDto;
    }

}
