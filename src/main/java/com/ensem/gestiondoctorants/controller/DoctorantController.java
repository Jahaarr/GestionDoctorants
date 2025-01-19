package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorantController {

    @Autowired
    private DoctorantRepository doctorantRepository;
    @GetMapping("/doctorant/dashboard")
    public String doctorantDashboard() {
        return "doctorant/dashboard";
    }
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("doctorant", new Doctorant());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistration(Doctorant doctorant) {
        doctorantRepository.save(doctorant);
        return "redirect:/dashboard";
    }
}
