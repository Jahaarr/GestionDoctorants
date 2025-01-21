package com.ensem.gestiondoctorants.service;

import com.ensem.gestiondoctorants.model.DocumentTemplate;
import com.ensem.gestiondoctorants.repository.DocumentTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentTemplateService {

    @Autowired
    private DocumentTemplateRepository repository;

    // Save or update a template
    public void saveTemplate(String documentType, String templateContent) {
        DocumentTemplate template = repository.findByDocumentType(documentType)
                .orElse(new DocumentTemplate());
        template.setDocumentType(documentType);
        template.setTemplateContent(templateContent);
        repository.save(template);
    }

    // Retrieve a template by document type
    public Optional<DocumentTemplate> getTemplateByType(String documentType) {
        return repository.findByDocumentType(documentType);
    }
}
