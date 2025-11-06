package com.clinic.web;

import com.clinic.repo.AdmissionRepo;
import com.clinic.repo.EquipmentRepo;
import com.clinic.repo.RoomRepo;
import com.clinic.repo.StaffRepo;
import com.clinic.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/")
    public String home(Model model) {
        long patientCount = patientRepo.count();
        long staffCount = staffRepo.count();
        long roomCount = roomRepo.count();
        long equipmentCount = equipmentRepo.count();
        long admissionCount = admissionRepo.count();

        model.addAttribute("patientCount", patientCount);
        model.addAttribute("staffCount", staffCount);
        model.addAttribute("roomCount", roomCount);
        model.addAttribute("equipmentCount", equipmentCount);
        model.addAttribute("admissionCount", admissionCount);

        return "home";
    }
}
