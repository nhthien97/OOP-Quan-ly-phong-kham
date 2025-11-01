package com.clinic.web;

import com.clinic.domain.Room;
import com.clinic.repo.DepartmentRepo;
import com.clinic.repo.RoomRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepo repo;
    private final DepartmentRepo depRepo;

    public RoomController(RoomRepo repo, DepartmentRepo depRepo) {
        this.repo = repo;
        this.depRepo = depRepo;
    }

    // 🔍 DANH SÁCH + TÌM KIẾM
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("items", repo.findByCodeContainingIgnoreCase(keyword));
        } else {
            model.addAttribute("items", repo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "rooms/list";
    }

    // 🟢 FORM THÊM MỚI
    @GetMapping("/new")
    public String form(Model m) {
        m.addAttribute("item", new Room());
        m.addAttribute("departments", depRepo.findAll());
        return "rooms/form";
    }

    // 🟢 LƯU (THÊM MỚI)
    @PostMapping
    public String create(Room r, @RequestParam("department.id") Long depId) {
        if (r.getOccupied() == null) r.setOccupied(0);
        r.setDepartment(depRepo.findById(depId).orElse(null));
        repo.save(r);
        return "redirect:/rooms";
    }

    // 🟢 FORM CHỈNH SỬA
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model m) {
        m.addAttribute("item", repo.findById(id).orElseThrow());
        m.addAttribute("departments", depRepo.findAll());
        return "rooms/form";
    }

    // 🟢 CẬP NHẬT
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Room r, @RequestParam("department.id") Long depId) {
        r.setId(id);
        r.setDepartment(depRepo.findById(depId).orElse(null));
        repo.save(r);
        return "redirect:/rooms";
    }

    // 🗑️ XOÁ
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/rooms";
    }
}
