package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.service.DataImportExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private DataImportExportService dataImportExportService;
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
    }}
