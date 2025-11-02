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

    // 🧾 Danh sách nhân viên (có tìm kiếm)
    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("staffList",
                    staffRepo.findByCodeContainingIgnoreCaseOrFullNameContainingIgnoreCase(keyword, keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("staffList", staffRepo.findAll());
            model.addAttribute("keyword", "");
        }
        return "staff/list";
    }

    // ➕ Form thêm nhân viên
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("departments", departmentRepo.findAll());
        return "staff/form";
    }

    // ✏️ Form sửa nhân viên
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Staff s = staffRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên ID: " + id));
        model.addAttribute("staff", s);
        model.addAttribute("departments", departmentRepo.findAll());
        return "staff/form";
    }

    // 💾 Lưu (thêm / sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute Staff s, @RequestParam("department") Long departmentId) {
        s.setDepartment(departmentRepo.findById(departmentId).orElse(null));
        staffRepo.save(s);
        return "redirect:/staff";
    }

    // 🗑 Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        staffRepo.deleteById(id);
        return "redirect:/staff";
    }
}
