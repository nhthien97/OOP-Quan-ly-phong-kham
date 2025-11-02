package com.clinic.repo;

import com.clinic.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    // 🔍 Tìm kiếm gần đúng theo tên hoặc cơ sở
    List<Department> findByNameContainingIgnoreCaseOrFacilityContainingIgnoreCase(String name, String facility);
}
