package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@Controller
public class DoctorantController {

    @Autowired
    private DoctorantRepository doctorantRepository;

    // Dashboard view for a logged-in Doctorant
    @GetMapping("/doctorant/dashboard")
    public String doctorantDashboard(Model model) {
        // Example of adding dummy data if required
        // Replace it with the authenticated doctorant's data if using Spring Security
        model.addAttribute("doctorant", new Doctorant());
        return "doctorant/dashboard";
    }

    // Display the registration form
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("doctorant", new Doctorant()); // Add an empty Doctorant object to the model
        return "registration"; // Return the view for the registration form
    }
    @GetMapping("/doctorant/profile")
    public String viewProfile(Model model) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        // Find the doctorant by email
        Doctorant doctorant = doctorantRepository.findByEmail(currentUserEmail);

        // For debugging purposes
        System.out.println("Current user email: " + currentUserEmail);
        System.out.println("Found doctorant: " + (doctorant != null ? doctorant.getEmail() : "null"));

        if (doctorant == null) {
            // For development, you might want to use a sample doctorant
            doctorant = doctorantRepository.findAll().stream().findFirst().orElse(new Doctorant());
            // You might also want to log this situation
            System.out.println("Using fallback doctorant data");
        }

        model.addAttribute("doctorant", doctorant);
        return "doctorant/profile";
    }


    // Process the registration form submission
    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute Doctorant doctorant) {
        // Save the doctorant's information to the database
        doctorantRepository.save(doctorant);
        return "redirect:/doctorant/dashboard"; // Redirect to the dashboard after registration
    }
}
