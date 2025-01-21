package com.ensem.gestiondoctorants.service;

import com.ensem.gestiondoctorants.dto.DoctorantProfileDTO;
import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorantService {

    @Autowired
    private DoctorantRepository doctorantRepository;

    public boolean isFirstLogin(String cne) {
        Doctorant doctorant = doctorantRepository.findByCne(cne);
        return doctorant != null && doctorant.isFirstLogin();
    }

    public void updateDoctorantProfile(String cne, DoctorantProfileDTO profileData) {
        Doctorant doctorant = doctorantRepository.findByCne(cne);
        if (doctorant != null) {
            // Update all doctorant data
            doctorant.setNom(profileData.getNom());
            doctorant.setPrenom(profileData.getPrenom());
            doctorant.setCin(profileData.getCin());
            doctorant.setEmail(profileData.getEmail());
            doctorant.setNumTel(profileData.getNumTel());
            doctorant.setCodeApogee(profileData.getCodeApogee());
            doctorant.setSexe(profileData.getSexe());
            doctorant.setVilleNaissance(profileData.getVilleNaissance());
            doctorant.setNomArab(profileData.getNomArab());
            doctorant.setPrenomArab(profileData.getPrenomArab());
            doctorant.setVilleNaissanceArab(profileData.getVilleNaissanceArab());
            doctorant.setAnneeUniv(profileData.getAnneeUniv());
            doctorant.setCodEtp(profileData.getCodEtp());
            doctorant.setCodVrsVet(profileData.getCodVrsVet());
            doctorant.setCodDip(profileData.getCodDip());
            doctorant.setCodVrsVdi(profileData.getCodVrsVdi());
            doctorant.setNbrInsCyc(profileData.getNbrInsCyc());
            doctorant.setNbrInsDip(profileData.getNbrInsDip());
            doctorant.setMentionBac(profileData.getMentionBac());
            doctorant.setAnneeBac(profileData.getAnneeBac());
            doctorant.setLibDip(profileData.getLibDip());
            doctorant.setLibDipArb(profileData.getLibDipArb());
            doctorant.setFormationDoctorale(profileData.getFormationDoctorale());
            doctorant.setTitreThese(profileData.getTitreThese());
            doctorant.setDirecteurThese(profileData.getDirecteurThese());
            doctorant.setCodirecteurThese(profileData.getCodirecteurThese());
            doctorant.setCotutelle(profileData.getCotutelle());
            doctorant.setBourse(profileData.getBourse());
            doctorant.setDateNaissance(profileData.getDateNaissance());

            // Mark first login as completed
            doctorant.setFirstLogin(false);
            doctorantRepository.save(doctorant);
        }
    }

    public Doctorant getDoctorantByCne(String cne) {
        return doctorantRepository.findByCne(cne);
    }
}