
package com.clinic.web;

import com.clinic.domain.Patient;
import com.clinic.repo.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepo repo;
    public PatientController(PatientRepo repo){this.repo=repo;}

    @GetMapping public String list(Model model){ model.addAttribute("items", repo.findAll()); return "patients/list";}
    @GetMapping("/new") public String createForm(Model m){ m.addAttribute("item", new Patient()); return "patients/form";}
    @PostMapping public String create(Patient p){ repo.save(p); return "redirect:/patients";}
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model m){ m.addAttribute("item", repo.findById(id).orElseThrow()); return "patients/form";}
    @PostMapping("/{id}") public String update(@PathVariable Long id, Patient p){ p.setId(id); repo.save(p); return "redirect:/patients";}
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id){ repo.deleteById(id); return "redirect:/patients";}
}
