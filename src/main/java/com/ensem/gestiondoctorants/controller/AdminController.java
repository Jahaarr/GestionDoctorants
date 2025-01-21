package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import com.ensem.gestiondoctorants.service.DataImportExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private DataImportExportService dataImportExportService;

    @Autowired
    private DoctorantRepository doctorantRepository;
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/import-export")
    public String showImportExportPage() {
        return "admin/import-export";
    }

    @PostMapping("/admin/import-data")
    public String importData(@RequestParam("file") MultipartFile file, Model model) {
        try {
            dataImportExportService.processImport(file);
            model.addAttribute("message", "Données importées avec succès!");
        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'importation des données.");
            e.printStackTrace();
        }
        return "admin/import-export";
    }
    @GetMapping("/admin/manage-doctorants")
    public String manageDoctorants(Model model) {
        // Fetch all doctorants from the database
        List<Doctorant> doctorants = doctorantRepository.findAll();
        model.addAttribute("doctorants", doctorants);
        return "admin/manage-doctorants"; // Return the Thymeleaf template for managing doctorants
    }
    @GetMapping("/admin/doctorant/edit/{id}")
    public String editDoctorant(@PathVariable Long id, Model model) {
        Doctorant doctorant = doctorantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctorant non trouvé : " + id));
        model.addAttribute("doctorant", doctorant);
        return "admin/edit-doctorant";
    }
    @PostMapping("/admin/doctorant/edit")
    public String updateDoctorant(@ModelAttribute Doctorant doctorant) {
        doctorantRepository.save(doctorant); // Save the updated doctorant details
        return "redirect:/admin/manage-doctorants"; // Redirect to the manage doctorants page
    }

    @GetMapping("/admin/doctorant/delete/{id}")
    public String deleteDoctorant(@PathVariable Long id) {
        doctorantRepository.deleteById(id);
        return "redirect:/admin/manage-doctorants";
    }

}
