package com.clinic.web;

import com.clinic.domain.*;
import com.clinic.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admissions")
public class AdmissionController {

    private final AdmissionRepo admissionRepo;
    private final PatientRepo patientRepo;
    private final RoomRepo roomRepo;

    public AdmissionController(AdmissionRepo admissionRepo, PatientRepo patientRepo, RoomRepo roomRepo) {
        this.admissionRepo = admissionRepo;
        this.patientRepo = patientRepo;
        this.roomRepo = roomRepo;
    }

    // ✅ Hiển thị danh sách (có tìm kiếm)
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("items",
                    admissionRepo.findByPatient_FullNameContainingIgnoreCaseOrRoom_CodeContainingIgnoreCase(keyword, keyword));
        } else {
            model.addAttribute("items", admissionRepo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "admissions/list";
    }

    // ✅ Form thêm mới
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new Admission());
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("rooms", roomRepo.findAll());
        return "admissions/form";
    }

    // ✅ Form sửa
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Admission admission = admissionRepo.findById(id).orElseThrow();
        model.addAttribute("item", admission);
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("rooms", roomRepo.findAll());
        return "admissions/form";
    }

    // ✅ Lưu mới
    @PostMapping
    public String save(@ModelAttribute Admission admission) {
        admissionRepo.save(admission);
        return "redirect:/admissions";
    }

    // ✅ Cập nhật
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Admission admission) {
        admission.setId(id);
        admissionRepo.save(admission);
        return "redirect:/admissions";
    }

    // ✅ Xóa
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        admissionRepo.deleteById(id);
        return "redirect:/admissions";
    }
}
