package com.clinic.web;

import com.clinic.domain.Patient;
import com.clinic.repo.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepo repo;

    public PatientController(PatientRepo repo) {
        this.repo = repo;
    }

    // 🟢 1. HIỂN THỊ DANH SÁCH
    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", repo.findAll());
        return "patients/list";
    }

    // 🟢 2. FORM THÊM MỚI
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    // 🟢 3. FORM CHỈNH SỬA
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Patient patient = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bệnh nhân ID: " + id));
        model.addAttribute("patient", patient);
        return "patients/form";
    }

    // 🟢 4. LƯU (THÊM / CẬP NHẬT)
    @PostMapping("/save")
    public String save(@ModelAttribute Patient patient) {
        repo.save(patient);
        return "redirect:/patients";
    }

    // 🟢 5. XOÁ
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/patients";
    }
}
