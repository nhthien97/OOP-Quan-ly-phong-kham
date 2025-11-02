package com.clinic.web;

import com.clinic.domain.Room;
import com.clinic.repo.RoomRepo;
import com.clinic.repo.DepartmentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepo roomRepo;
    private final DepartmentRepo departmentRepo;

    public RoomController(RoomRepo roomRepo, DepartmentRepo departmentRepo) {
        this.roomRepo = roomRepo;
        this.departmentRepo = departmentRepo;
    }

    // ✅ DANH SÁCH + TÌM KIẾM THEO MÃ HOẶC LOẠI
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("rooms", roomRepo.findByCodeContainingIgnoreCaseOrTypeContainingIgnoreCase(keyword, keyword));
        } else {
            model.addAttribute("rooms", roomRepo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "rooms/list";
    }

    // ➕ FORM THÊM PHÒNG
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("departments", departmentRepo.findAll());
        return "rooms/form";
    }

    // 💾 LƯU (THÊM HOẶC SỬA)
    @PostMapping("/save")
    public String save(@ModelAttribute Room room, @RequestParam("department.id") Long depId) {
        if (room.getOccupied() == null) room.setOccupied(0);
        room.setDepartment(departmentRepo.findById(depId).orElse(null));
        roomRepo.save(room);
        return "redirect:/rooms";
    }

    // ✏️ SỬA
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Room room = roomRepo.findById(id).orElseThrow();
        model.addAttribute("room", room);
        model.addAttribute("departments", departmentRepo.findAll());
        return "rooms/form";
    }

    // 🗑 XOÁ
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roomRepo.deleteById(id);
        return "redirect:/rooms";
    }
}
