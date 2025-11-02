package com.clinic.repo;

import com.clinic.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
    List<Equipment> findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(String code, String name);
}
