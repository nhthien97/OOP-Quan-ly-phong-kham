package com.clinic.repo;

import com.clinic.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    Optional<Patient> findByCode(String code);

    // 🔍 Tìm kiếm gần đúng theo tên hoặc mã (không phân biệt hoa thường)
    List<Patient> findByFullNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
