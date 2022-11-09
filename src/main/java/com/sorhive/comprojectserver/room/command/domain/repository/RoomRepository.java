package com.sorhive.comprojectserver.room.command.domain.repository;

import com.sorhive.comprojectserver.room.command.domain.room.Room;
import com.sorhive.comprojectserver.room.command.domain.room.RoomId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : RoomRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           RoomRepository 최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface RoomRepository extends Repository<Room, Long> {
    void save(Room room);
    Optional<Room> findById(Long id);
    List<Room> findAll();
}