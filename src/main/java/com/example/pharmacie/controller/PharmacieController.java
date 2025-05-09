package com.example.pharmacie.controller;

import com.example.pharmacie.model.Medicament;
import com.example.pharmacie.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PharmacieController {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @GetMapping("/medicaments")
    public String afficherMedicaments(Model model) {
        model.addAttribute("medicaments", medicamentRepository.findAll());
        model.addAttribute("medicament", new Medicament());
        return "medicaments";
    }

    @PostMapping("/ajouter")
    public String ajouterMedicament(@ModelAttribute Medicament medicament) {
        medicamentRepository.save(medicament);
        return "redirect:/medicaments";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        medicamentRepository.deleteById(id);
        return "redirect:/medicaments";
    }

    @GetMapping("/rechercher")
    public String rechercher(@RequestParam String nom, Model model) {
        Medicament m = medicamentRepository.findByNom(nom);
        model.addAttribute("medicaments", m != null ? java.util.List.of(m) : java.util.List.of());
        model.addAttribute("medicament", new Medicament());
        return "medicaments";
    }
}
