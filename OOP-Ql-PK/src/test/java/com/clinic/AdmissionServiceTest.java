package com.clinic;

import com.clinic.domain.*;
import com.clinic.repo.*;
import com.clinic.service.AdmissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AdmissionServiceTest {

    @Autowired private PatientRepo patientRepo;
    @Autowired private RoomRepo roomRepo;
    @Autowired private AdmissionRepo admissionRepo;
    @Autowired private AdmissionService service;

    @Test @Transactional
    void admitAndCheckout_ShouldUpdateOccupied() {
        Patient p = patientRepo.save(Patient.builder().code("BN001").fullName("Nguyen Van A").build());
        Room r = roomRepo.save(Room.builder().code("PK1").type("giuong").capacity(1).occupied(0).build());
        var ad = service.admit("BN001","PK1");
        Assertions.assertEquals(1, roomRepo.findById(r.getId()).orElseThrow().getOccupied());
        service.checkout(ad.getId());
        Assertions.assertEquals(0, roomRepo.findById(r.getId()).orElseThrow().getOccupied());
    }
}
