package com.ensem.gestiondoctorants.controller;

import com.ensem.gestiondoctorants.model.*;
import com.ensem.gestiondoctorants.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class DoctorantController {

    @Autowired
    private DoctorantRepository doctorantRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private PatentRepository patentRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ProgressRepository progressRepository;

    // Redirect to registration if doctorant has incomplete data
    @GetMapping("/doctorant/first-login")
    public String firstLogin(Model model) {
        // Get current authenticated user's CNE
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String cne = authentication.getName();

        // Fetch the doctorant by CNE
        Doctorant doctorant = doctorantRepository.findByCne(cne);
        if (doctorant == null) {
            // If doctorant doesn't exist, create new one
            doctorant = new Doctorant();
            doctorant.setCne(cne);
            doctorant.setFirstLogin(true);
            doctorantRepository.save(doctorant);
        }

        // Check firstLogin flag
        if (doctorant.isFirstLogin()) {
            model.addAttribute("doctorant", doctorant);
            return "doctorant/registration"; // Show the initial setup form
        } else {
            return "redirect:/doctorant/dashboard"; // Redirect to dashboard if not first login
        }
    }

    @PostMapping("/doctorant/first-login")
    public String processFirstLogin(@ModelAttribute Doctorant doctorant) {
        // Get current authenticated user's CNE
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String cne = authentication.getName();

        // Fetch existing doctorant
        Doctorant existingDoctorant = doctorantRepository.findByCne(cne);
        if (existingDoctorant != null) {
            // Keep the original CNE and CIN
            String originalCin = existingDoctorant.getCin();
            String originalCne = existingDoctorant.getCne();

            // Update all fields
            existingDoctorant.setNom(doctorant.getNom());
            existingDoctorant.setPrenom(doctorant.getPrenom());
            existingDoctorant.setCin(originalCin); // Keep original CIN
            existingDoctorant.setCne(originalCne); // Keep original CNE
            existingDoctorant.setEmail(doctorant.getEmail());
            existingDoctorant.setNumTel(doctorant.getNumTel());
            existingDoctorant.setCodeApogee(doctorant.getCodeApogee());
            existingDoctorant.setSexe(doctorant.getSexe());
            existingDoctorant.setVilleNaissance(doctorant.getVilleNaissance());
            existingDoctorant.setNomArab(doctorant.getNomArab());
            existingDoctorant.setPrenomArab(doctorant.getPrenomArab());
            existingDoctorant.setVilleNaissanceArab(doctorant.getVilleNaissanceArab());
            existingDoctorant.setAnneeUniv(doctorant.getAnneeUniv());
            existingDoctorant.setCodEtp(doctorant.getCodEtp());
            existingDoctorant.setCodVrsVet(doctorant.getCodVrsVet());
            existingDoctorant.setCodDip(doctorant.getCodDip());
            existingDoctorant.setCodVrsVdi(doctorant.getCodVrsVdi());
            existingDoctorant.setNbrInsCyc(doctorant.getNbrInsCyc());
            existingDoctorant.setNbrInsDip(doctorant.getNbrInsDip());
            existingDoctorant.setMentionBac(doctorant.getMentionBac());
            existingDoctorant.setAnneeBac(doctorant.getAnneeBac());
            existingDoctorant.setLibDip(doctorant.getLibDip());
            existingDoctorant.setLibDipArb(doctorant.getLibDipArb());
            existingDoctorant.setFormationDoctorale(doctorant.getFormationDoctorale());
            existingDoctorant.setTitreThese(doctorant.getTitreThese());
            existingDoctorant.setDirecteurThese(doctorant.getDirecteurThese());
            existingDoctorant.setCodirecteurThese(doctorant.getCodirecteurThese());
            existingDoctorant.setCotutelle(doctorant.getCotutelle());
            existingDoctorant.setBourse(doctorant.getBourse());
            existingDoctorant.setDateNaissance(doctorant.getDateNaissance());

            // Set firstLogin to false as the profile is now complete
            existingDoctorant.setFirstLogin(false);

            // Save the updated doctorant
            doctorantRepository.save(existingDoctorant);
        }

        return "redirect:/doctorant/dashboard";
    }


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
        String cne = authentication.getName();

        // Find the doctorant by email
        Doctorant doctorant = doctorantRepository.findByCne(cne);

        // For debugging purposes
        System.out.println("Current user CNE: " + cne);
        System.out.println("Found doctorant: " + doctorant);

        model.addAttribute("doctorant", doctorant);
        return "doctorant/profile";
    }



    // Show document requests page
    @GetMapping("/doctorant/document-requests")
    public String showDocumentRequestsPage() {
        return "doctorant/document-requests";
    }

    // Handle standard document requests
    @PostMapping("/doctorant/request-standard")
    public String handleStandardRequest(@RequestParam("documentType") String documentType) {
        // Process standard document request (e.g., save to database or notify admin)
        System.out.println("Standard Document Requested: " + documentType);
        return "redirect:/doctorant/document-requests?success";
    }

    // Handle exceptional document requests
    @PostMapping("/doctorant/request-exceptional")
    public String handleExceptionalRequest(
            @RequestParam("exceptionalDocumentType") String exceptionalDocumentType,
            @RequestParam("justification") String justification,
            @RequestParam("attachment") MultipartFile attachment) {
        // Process exceptional document request (e.g., save to database or notify admin)
        System.out.println("Exceptional Document Requested: " + exceptionalDocumentType);
        System.out.println("Justification: " + justification);
        System.out.println("Attachment: " + attachment.getOriginalFilename());
        return "redirect:/doctorant/document-requests?success";
    }
    // Display academic data input page
    @GetMapping("/doctorant/academic-data")
    public String showAcademicDataPage() {
        return "doctorant/academic-data";
    }

    // Save formation
    @PostMapping("/doctorant/save-formation")
    public String saveFormation(@RequestParam("formation") String formation,
                                @RequestParam("date") String date,
                                @RequestParam("location") String location) {
        Formation newFormation = new Formation(formation, date, location);
        formationRepository.save(newFormation);
        return "redirect:/doctorant/academic-data?success";
    }

    // Save conference
    @PostMapping("/doctorant/save-conference")
    public String saveConference(@RequestParam("conference") String conference,
                                 @RequestParam("date") String date,
                                 @RequestParam("location") String location) {
        Conference newConference = new Conference(conference, date, location);
        conferenceRepository.save(newConference);
        return "redirect:/doctorant/academic-data?success";
    }

    // Save article
    @PostMapping("/doctorant/save-article")
    public String saveArticle(@RequestParam("title") String title,
                              @RequestParam("journal") String journal,
                              @RequestParam("date") String date) {
        Article newArticle = new Article(title, journal, date);
        articleRepository.save(newArticle);
        return "redirect:/doctorant/academic-data?success";
    }

    // Save patent
    @PostMapping("/doctorant/save-patent")
    public String savePatent(@RequestParam("title") String title,
                             @RequestParam("date") String date,
                             @RequestParam("description") String description) {
        Patent newPatent = new Patent(title, date, description);
        patentRepository.save(newPatent);
        return "redirect:/doctorant/academic-data?success";
    }
    // Show the claims page
    @GetMapping("/doctorant/claims")
    public String showClaimsPage(Model model) {
        model.addAttribute("claim", new Claim()); // Add a new Claim object for the form
        return "doctorant/claims";
    }

    // Handle claim submission
    @PostMapping("/doctorant/claims")
    public String submitClaim(@ModelAttribute Claim claim) {
        claimRepository.save(claim); // Save the claim to the database
        return "redirect:/doctorant/claims?success";
    }
    // Show the progress page
    @GetMapping("/doctorant/progress")
    public String showProgressPage(Model model) {
        // Fetch all progress steps (replace with specific doctorant logic if needed)
        List<Progress> progressSteps = progressRepository.findAll();
        model.addAttribute("progressSteps", progressSteps);
        return "doctorant/progress";
    }

}
