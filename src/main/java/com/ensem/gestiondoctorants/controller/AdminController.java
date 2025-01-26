package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.model.DocumentTemplate;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import com.ensem.gestiondoctorants.service.DataImportExportService;
import com.ensem.gestiondoctorants.service.DocumentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;


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
        List<Doctorant> doctorants = doctorantRepository.findAll();
        model.addAttribute("doctorants", doctorants);
        return "admin/manage-doctorants";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/admin/add-doctorant")
    public String addDoctorant(@RequestParam String cin, @RequestParam String cne) {
        if (doctorantRepository.findByCin(cin) != null || doctorantRepository.findByCne(cne) != null) {
            return "redirect:/admin/manage-doctorants?error=exists";
        }

        Doctorant doctorant = new Doctorant();
        doctorant.setCin(cin);
        doctorant.setPassword(passwordEncoder.encode(cin));
        doctorant.setCne(cne);
        doctorant.setFirstLogin(true);
        doctorant.setRole("DOCTORANT");
        doctorant.setDateNaissance(LocalDate.of(1900, 1, 1));
        doctorantRepository.save(doctorant);

        return "redirect:/admin/manage-doctorants?success=added";
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
        Doctorant existingDoctorant = doctorantRepository.findById(doctorant.getId())
                .orElseThrow(() -> new IllegalArgumentException("Doctorant non trouvé"));

        // Update fields while preserving original CIN and CNE
        String originalCin = existingDoctorant.getCin();
        String originalCne = existingDoctorant.getCne();

        existingDoctorant.setNom(doctorant.getNom());
        existingDoctorant.setPrenom(doctorant.getPrenom());
        existingDoctorant.setEmail(doctorant.getEmail());
        existingDoctorant.setNumTel(doctorant.getNumTel());
        existingDoctorant.setDateNaissance(doctorant.getDateNaissance());
        existingDoctorant.setSexe(doctorant.getSexe());

        // Preserve original CIN and CNE
        existingDoctorant.setCin(originalCin);
        existingDoctorant.setCne(originalCne);

        doctorantRepository.save(existingDoctorant);
        return "redirect:/admin/manage-doctorants?success=updated";
    }

    @GetMapping("/admin/doctorant/delete/{id}")
    public String deleteDoctorant(@PathVariable Long id) {
        doctorantRepository.deleteById(id);
        return "redirect:/admin/manage-doctorants?success=deleted";
    }

    @Autowired
    private DocumentTemplateService documentTemplateService;

    @GetMapping("/admin/edit-documents")
    public String showEditDocumentsPage(Model model) {
        List<DocumentTemplate> templates = documentTemplateService.getAllTemplates();
        model.addAttribute("documentTypes", List.of("Attestation d'inscription", "Diplôme", "Attestation de réussite"));
        model.addAttribute("templates", templates);
        return "admin/edit-documents";
    }

    @GetMapping("/admin/get-template/{type}")
    @ResponseBody
    public String getDocumentTemplate(@PathVariable String type) {
        return documentTemplateService.getTemplateContent(type);
    }

    @PostMapping("/admin/save-document-template")
    public String saveDocumentTemplate(
            @RequestParam("documentType") String documentType,
            @RequestParam("templateContent") String templateContent) {

        documentTemplateService.saveTemplate(documentType, templateContent);
        return "redirect:/admin/edit-documents?success";
    }
}