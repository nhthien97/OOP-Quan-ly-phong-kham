
package com.clinic.web;

import com.clinic.domain.*;
import com.clinic.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepo repo;
    private final DepartmentRepo depRepo;
    public RoomController(RoomRepo repo, DepartmentRepo depRepo){this.repo=repo; this.depRepo=depRepo;}

    @GetMapping public String list(Model m){ m.addAttribute("items", repo.findAll()); return "rooms/list";}
    @GetMapping("/new") public String form(Model m){ m.addAttribute("item", new Room()); m.addAttribute("departments", depRepo.findAll()); return "rooms/form";}
    @PostMapping public String create(Room r){ if (r.getOccupied()==null) r.setOccupied(0); repo.save(r); return "redirect:/rooms";}
    @GetMapping("/{id}/edit") public String edit(@PathVariable Long id, Model m){ m.addAttribute("item", repo.findById(id).orElseThrow()); m.addAttribute("departments", depRepo.findAll()); return "rooms/form";}
    @PostMapping("/{id}") public String update(@PathVariable Long id, Room r){ r.setId(id); repo.save(r); return "redirect:/rooms";}
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id){ repo.deleteById(id); return "redirect:/rooms";}
}
