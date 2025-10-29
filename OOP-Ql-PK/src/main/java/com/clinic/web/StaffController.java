
package com.clinic.web;

import com.clinic.domain.*;
import com.clinic.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepo repo;
    private final DepartmentRepo depRepo;
    public StaffController(StaffRepo repo, DepartmentRepo depRepo){this.repo=repo; this.depRepo = depRepo;}

    @GetMapping public String list(Model model){ model.addAttribute("items", repo.findAll()); return "staff/list";}
    @GetMapping("/new") public String createForm(Model m){ m.addAttribute("item", new Staff()); m.addAttribute("departments", depRepo.findAll()); return "staff/form";}
    @PostMapping public String create(Staff s){ repo.save(s); return "redirect:/staff";}
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model m){ m.addAttribute("item", repo.findById(id).orElseThrow()); m.addAttribute("departments", depRepo.findAll()); return "staff/form";}
    @PostMapping("/{id}") public String update(@PathVariable Long id, Staff s){ s.setId(id); repo.save(s); return "redirect:/staff";}
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id){ repo.deleteById(id); return "redirect:/staff";}
}
