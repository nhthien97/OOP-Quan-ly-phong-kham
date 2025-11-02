package com.clinic.repo;

import com.clinic.domain.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdmissionRepo extends JpaRepository<Admission, Long> {
    // 🔍 Tìm kiếm gần đúng theo tên bệnh nhân hoặc mã phòng
    List<Admission> findByPatient_FullNameContainingIgnoreCaseOrRoom_CodeContainingIgnoreCase(String name, String code);
}
