package com.clinic.web;

import com.clinic.domain.Department;
import com.clinic.repo.DepartmentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepo repo;

    public DepartmentController(DepartmentRepo repo) {
        this.repo = repo;
    }

    // 🟢 Hiển thị danh sách
    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", repo.findAll());
        return "departments/list";
    }

    // 🟢 Hiển thị form thêm mới
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new Department());
        return "departments/form";
    }

    // 🟢 Hiển thị form sửa
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Department d = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khoa với ID: " + id));
        model.addAttribute("item", d);
        return "departments/form";
    }

    // 🟢 Lưu (tạo hoặc cập nhật)
    @PostMapping
    public String save(@ModelAttribute Department d) {
        repo.save(d);
        return "redirect:/departments";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Department d) {
        d.setId(id);
        repo.save(d);
        return "redirect:/departments";
    }

    // 🟢 Xoá
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/departments";
    }
}
