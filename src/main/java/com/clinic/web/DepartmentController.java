package com.clinic.web;

import com.clinic.domain.Department;
import com.clinic.repo.DepartmentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepo departmentRepo;

    public DepartmentController(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    // 🧾 DANH SÁCH (CÓ TÌM KIẾM)
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("departments",
                departmentRepo.findByNameContainingIgnoreCaseOrFacilityContainingIgnoreCase(keyword, keyword));
        } else {
            model.addAttribute("departments", departmentRepo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "departments/list";
    }

    // ➕ THÊM MỚI
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/form";
    }

    // ✏️ SỬA
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Department d = departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khoa với ID: " + id));
        model.addAttribute("department", d);
        return "departments/form";
    }

    // 💾 LƯU (THÊM HOẶC SỬA)
    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        departmentRepo.save(department);
        return "redirect:/departments";
    }

    // 🗑 XOÁ
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        departmentRepo.deleteById(id);
        return "redirect:/departments";
    }
}
