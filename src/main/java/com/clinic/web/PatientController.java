package com.clinic.web;

import com.clinic.domain.Patient;
import com.clinic.repo.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepo patientRepo;

    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    // 🧾 Danh sách bệnh nhân (có tìm kiếm)
    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("patients",
                    patientRepo.findByFullNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword, keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("keyword", "");
        }
        return "patients/list";
    }

    // ➕ Thêm bệnh nhân mới
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    // 💾 Lưu (thêm hoặc sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute Patient patient) {
        patientRepo.save(patient);
        return "redirect:/patients";
    }

    // ✏️ Sửa thông tin
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientRepo.findById(id).orElseThrow());
        return "patients/form";
    }

    // 🗑️ Xóa bệnh nhân
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        patientRepo.deleteById(id);
        return "redirect:/patients";
    }
}
