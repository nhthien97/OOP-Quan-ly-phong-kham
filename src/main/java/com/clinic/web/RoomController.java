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

    // ✅ DANH SÁCH + TÌM KIẾM THEO CODE
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("items", roomRepo.findByCodeContainingIgnoreCase(keyword));
        } else {
            model.addAttribute("items", roomRepo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "rooms/list";
    }

    // ✅ HIỂN THỊ FORM THÊM PHÒNG
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("item", new Room());
        model.addAttribute("departments", departmentRepo.findAll());
        return "rooms/form";
    }

    // ✅ XỬ LÝ TẠO MỚI PHÒNG
    @PostMapping
    public String create(Room room, @RequestParam("department.id") Long depId) {
        if (room.getOccupied() == null) room.setOccupied(0);
        room.setDepartment(departmentRepo.findById(depId).orElse(null));
        roomRepo.save(room);
        return "redirect:/rooms";
    }

    // ✅ FORM SỬA PHÒNG
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Room room = roomRepo.findById(id).orElseThrow();
        model.addAttribute("item", room);
        model.addAttribute("departments", departmentRepo.findAll());
        return "rooms/form";
    }

    // ✅ CẬP NHẬT PHÒNG
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Room room, @RequestParam("department.id") Long depId) {
        room.setId(id);
        room.setDepartment(departmentRepo.findById(depId).orElse(null));
        roomRepo.save(room);
        return "redirect:/rooms";
    }

    // ✅ XOÁ PHÒNG
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        roomRepo.deleteById(id);
        return "redirect:/rooms";
    }
}
