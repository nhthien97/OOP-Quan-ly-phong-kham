package com.clinic.web;

import com.clinic.domain.Equipment;
import com.clinic.repo.EquipmentRepo;
import com.clinic.repo.RoomRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentRepo repo;
    private final RoomRepo roomRepo;

    public EquipmentController(EquipmentRepo repo, RoomRepo roomRepo) {
        this.repo = repo;
        this.roomRepo = roomRepo;
    }

    // ✅ Danh sách + tìm kiếm
    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("items", repo.findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(keyword, keyword));
        } else {
            model.addAttribute("items", repo.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "equipment/list";
    }

    // ✅ Form thêm mới
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("item", new Equipment());
        model.addAttribute("rooms", roomRepo.findAll());
        return "equipment/form";
    }

    // ✅ Lưu (thêm/sửa)
    @PostMapping
    public String save(@ModelAttribute Equipment e, @RequestParam(value = "room.id", required = false) Long roomId) {
        if (roomId != null) {
            e.setRoom(roomRepo.findById(roomId).orElse(null));
        }
        repo.save(e);
        return "redirect:/equipment";
    }

    // ✅ Form sửa
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("item", repo.findById(id).orElseThrow());
        model.addAttribute("rooms", roomRepo.findAll());
        return "equipment/form";
    }

    // ✅ Xóa
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/equipment";
    }
}
