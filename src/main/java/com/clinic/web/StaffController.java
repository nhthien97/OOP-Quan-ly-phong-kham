package com.clinic.web;

import com.clinic.domain.Staff;
import com.clinic.repo.DepartmentRepo;
import com.clinic.repo.StaffRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffRepo staffRepo;
    private final DepartmentRepo departmentRepo;

    public StaffController(StaffRepo staffRepo, DepartmentRepo departmentRepo) {
        this.staffRepo = staffRepo;
        this.departmentRepo = departmentRepo;
    }

    // 🟢 DANH SÁCH (CÓ TÌM KIẾM)
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("items", staffRepo.findByFullNameContainingIgnoreCase(keyword));
        } else {
            model.addAttribute("items", staffRepo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "staff/list";
    }

    // 🟢 FORM THÊM
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new Staff());
        model.addAttribute("departments", departmentRepo.findAll());
        return "staff/form";
    }

    // 🟢 FORM SỬA
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Staff s = staffRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên với ID: " + id));
        model.addAttribute("item", s);
        model.addAttribute("departments", departmentRepo.findAll());
        return "staff/form";
    }

    // 🟢 LƯU (THÊM / SỬA)
    @PostMapping
    public String createOrUpdate(
            @ModelAttribute Staff s,
            @RequestParam("department") Long departmentId
    ) {
        s.setDepartment(departmentRepo.findById(departmentId).orElse(null));
        staffRepo.save(s);
        return "redirect:/staff";
    }

    // 🟢 XOÁ
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        staffRepo.deleteById(id);
        return "redirect:/staff";
    }
}
