
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
    public DepartmentController(DepartmentRepo repo){this.repo=repo;}

    @GetMapping public String list(Model m){ m.addAttribute("items", repo.findAll()); return "departments/list";}
    @GetMapping("/new") public String form(Model m){ m.addAttribute("item", new Department()); return "departments/form";}
    @PostMapping public String create(Department d){ repo.save(d); return "redirect:/departments";}
    @GetMapping("/{id}/edit") public String edit(@PathVariable Long id, Model m){ m.addAttribute("item", repo.findById(id).orElseThrow()); return "departments/form";}
    @PostMapping("/{id}") public String update(@PathVariable Long id, Department d){ d.setId(id); repo.save(d); return "redirect:/departments";}
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id){ repo.deleteById(id); return "redirect:/departments";}
}
