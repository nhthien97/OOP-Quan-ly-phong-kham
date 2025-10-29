
package com.clinic.web;

import com.clinic.domain.*;
import com.clinic.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentRepo repo;
    private final RoomRepo roomRepo;
    public EquipmentController(EquipmentRepo repo, RoomRepo roomRepo){this.repo=repo; this.roomRepo=roomRepo;}

    @GetMapping public String list(Model m){ m.addAttribute("items", repo.findAll()); return "equipment/list";}
    @GetMapping("/new") public String form(Model m){ m.addAttribute("item", new Equipment()); m.addAttribute("rooms", roomRepo.findAll()); return "equipment/form";}
    @PostMapping public String create(Equipment e){ repo.save(e); return "redirect:/equipment";}
    @GetMapping("/{id}/edit") public String edit(@PathVariable Long id, Model m){ m.addAttribute("item", repo.findById(id).orElseThrow()); m.addAttribute("rooms", roomRepo.findAll()); return "equipment/form";}
    @PostMapping("/{id}") public String update(@PathVariable Long id, Equipment e){ e.setId(id); repo.save(e); return "redirect:/equipment";}
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id){ repo.deleteById(id); return "redirect:/equipment";}
}
