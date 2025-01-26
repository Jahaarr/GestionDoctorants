package com.ensem.gestiondoctorants.service;

import com.ensem.gestiondoctorants.model.DocumentTemplate;
import com.ensem.gestiondoctorants.repository.DocumentTemplateRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DocumentTemplateService {
    @Autowired
    private DocumentTemplateRepository repository;

    public void saveTemplate(String type, String content) {
        DocumentTemplate template = repository.findByType(type)
                .orElse(new DocumentTemplate());
        template.setType(type);
        template.setContent(content);
        repository.save(template);
    }

    public String getTemplateContent(String type) {
        return repository.findByType(type)
                .map(DocumentTemplate::getContent)
                .orElse("");
    }

    public List<DocumentTemplate> getAllTemplates() {
        return repository.findAll();
    }
    @Value("${spring.resources.static-locations:classpath:/templates/documents/}")
    private String documentsPath;

    public String readDocxTemplate(String type) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(documentsPath + type + ".docx"))) {
            return doc.getParagraphs().stream()
                    .map(XWPFParagraph::getText)
                    .collect(Collectors.joining("\n"));
        }
    }
}