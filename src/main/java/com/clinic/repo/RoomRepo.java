package com.clinic.repo;

import com.clinic.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {

    Room findByCode(String code);

    // 🔍 Tìm kiếm gần đúng theo mã hoặc loại phòng
    List<Room> findByCodeContainingIgnoreCaseOrTypeContainingIgnoreCase(String code, String type);
}
