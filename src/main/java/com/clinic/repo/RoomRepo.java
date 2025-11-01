package com.clinic.repo;

import com.clinic.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Room findByCode(String code);

    // 🔍 Tìm kiếm theo mã phòng (không phân biệt hoa thường)
    List<Room> findByCodeContainingIgnoreCase(String keyword);
}
