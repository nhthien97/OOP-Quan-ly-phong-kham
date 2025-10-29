
package com.clinic.service;

import com.clinic.domain.*;
import com.clinic.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AdmissionService {
    private final PatientRepo patientRepo;
    private final RoomRepo roomRepo;
    private final AdmissionRepo admissionRepo;

    public AdmissionService(PatientRepo p, RoomRepo r, AdmissionRepo a) {
        this.patientRepo = p; this.roomRepo = r; this.admissionRepo = a;
    }

    /**
     * Phương thức hoạt động chính: nhận bệnh nhân vào phòng/giường.
     * - Kiểm tra phòng còn chỗ trống
     * - Tăng occupied, lưu Admission
     * - Xử lý lỗi có giao dịch đảm bảo nhất quán (Transactional)
     */
    @Transactional
    public Admission admit(String patientCode, String roomCode) {
        Patient patient = patientRepo.findByCode(patientCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bệnh nhân: " + patientCode));
        Room room = roomRepo.findByCode(roomCode);
        if (room == null) throw new IllegalArgumentException("Không tìm thấy phòng: " + roomCode);
        int occ = room.getOccupied() == null ? 0 : room.getOccupied();
        int cap = room.getCapacity() == null ? 0 : room.getCapacity();
        if (occ >= cap) throw new IllegalStateException("Phòng đã đầy");
        room.setOccupied(occ + 1);
        Admission ad = Admission.builder()
                .patient(patient).room(room).checkIn(LocalDateTime.now()).build();
        roomRepo.save(room);
        return admissionRepo.save(ad);
    }

    @Transactional
    public Admission checkout(long admissionId) {
        Admission ad = admissionRepo.findById(admissionId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu nhận"));
        if (ad.getCheckOut() != null) return ad; // đã trả phòng
        Room room = ad.getRoom();
        int occ = room.getOccupied() == null ? 0 : room.getOccupied();
        room.setOccupied(Math.max(0, occ - 1));
        ad.setCheckOut(LocalDateTime.now());
        roomRepo.save(room);
        return admissionRepo.save(ad);
    }
}
