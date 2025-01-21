package com.ensem.gestiondoctorants.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.ensem.gestiondoctorants.dto.DoctorantProfileDTO;
import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.service.DoctorantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/default")
    public String redirectAfterLogin(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DOCTORANT"))) {
            return "redirect:/doctorant/dashboard";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/login?error";
    }

    @Autowired
    private DoctorantService doctorantService;
    @GetMapping("/doctorant/initial-setup")
    public String showInitialSetup(Model model) {
        // Get current authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cne = auth.getName(); // Assuming username is CNE

        Doctorant doctorant = doctorantService.getDoctorantByCne(cne);

        // Create and populate DTO with existing data
        DoctorantProfileDTO profileDTO = new DoctorantProfileDTO();
        if (doctorant != null) {
            // Set existing values
            profileDTO.setCne(doctorant.getCne());
            profileDTO.setNom(doctorant.getNom());
            profileDTO.setPrenom(doctorant.getPrenom());
            profileDTO.setCin(doctorant.getCin());
            profileDTO.setEmail(doctorant.getEmail());
            profileDTO.setNumTel(doctorant.getNumTel());
            profileDTO.setCodeApogee(doctorant.getCodeApogee());
            profileDTO.setSexe(doctorant.getSexe());
            profileDTO.setVilleNaissance(doctorant.getVilleNaissance());
            profileDTO.setNomArab(doctorant.getNomArab());
            profileDTO.setPrenomArab(doctorant.getPrenomArab());
            profileDTO.setVilleNaissanceArab(doctorant.getVilleNaissanceArab());
            profileDTO.setAnneeUniv(doctorant.getAnneeUniv());
            profileDTO.setCodEtp(doctorant.getCodEtp());
            profileDTO.setCodVrsVet(doctorant.getCodVrsVet());
            profileDTO.setCodDip(doctorant.getCodDip());
            profileDTO.setCodVrsVdi(doctorant.getCodVrsVdi());
            profileDTO.setNbrInsCyc(doctorant.getNbrInsCyc());
            profileDTO.setNbrInsDip(doctorant.getNbrInsDip());
            profileDTO.setMentionBac(doctorant.getMentionBac());
            profileDTO.setAnneeBac(doctorant.getAnneeBac());
            profileDTO.setLibDip(doctorant.getLibDip());
            profileDTO.setLibDipArb(doctorant.getLibDipArb());
            profileDTO.setFormationDoctorale(doctorant.getFormationDoctorale());
            profileDTO.setTitreThese(doctorant.getTitreThese());
            profileDTO.setDirecteurThese(doctorant.getDirecteurThese());
            profileDTO.setCodirecteurThese(doctorant.getCodirecteurThese());
            profileDTO.setCotutelle(doctorant.getCotutelle());
            profileDTO.setBourse(doctorant.getBourse());
            profileDTO.setDateNaissance(doctorant.getDateNaissance());
        }

        model.addAttribute("profileData", profileDTO);
        return "doctorant/initial-setup";
    }

    @PostMapping("/doctorant/initial-setup")
    public String processInitialSetup(@ModelAttribute("profileData") DoctorantProfileDTO profileData) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cne = auth.getName();

        doctorantService.updateDoctorantProfile(cne, profileData);
        return "redirect:/doctorant/dashboard";
    }
}
