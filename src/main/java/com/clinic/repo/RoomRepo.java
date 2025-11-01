package com.clinic.repo;

import com.clinic.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {

    // ✅ Tìm chính xác theo mã (ví dụ: dùng để kiểm tra trùng mã phòng)
    Room findByCode(String code);

    // ✅ Tìm kiếm gần đúng theo mã phòng (case-insensitive)
    List<Room> findByCodeContainingIgnoreCase(String keyword);
}
