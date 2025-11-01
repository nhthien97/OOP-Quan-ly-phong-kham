package com.clinic.repo;

import com.clinic.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff, Long> {
    // Tìm theo fullName hoặc code
    List<Staff> findByCodeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String code, String fullName);
}
