
package com.clinic.web;

import com.clinic.domain.*;
import com.clinic.repo.*;
import com.clinic.service.AdmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admissions")
public class AdmissionController {
    private final AdmissionService service;
    private final AdmissionRepo repo;
    private final PatientRepo patientRepo;
    private final RoomRepo roomRepo;

    public AdmissionController(AdmissionService s, AdmissionRepo repo, PatientRepo p, RoomRepo r){
        this.service=s; this.repo=repo; this.patientRepo=p; this.roomRepo=r;
    }

    @GetMapping public String list(Model m){ m.addAttribute("items", repo.findAll()); return "admissions/list";}
    @GetMapping("/new") public String form(Model m){
        m.addAttribute("patients", patientRepo.findAll());
        m.addAttribute("rooms", roomRepo.findAll());
        return "admissions/form";
    }
    @PostMapping public String create(@RequestParam String patientCode, @RequestParam String roomCode){
        service.admit(patientCode, roomCode);
        return "redirect:/admissions";
    }
    @PostMapping("/{id}/checkout") public String checkout(@PathVariable Long id){
        service.checkout(id);
        return "redirect:/admissions";
    }
}
