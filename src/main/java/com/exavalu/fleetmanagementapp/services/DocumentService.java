package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Document;
import com.exavalu.fleetmanagementapp.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(Integer id) {
        return documentRepository.findById(id);
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Optional<Document> updateDocument(Integer id, Document documentDetails) {
        return documentRepository.findById(id).map(document -> {
            document.setFileName(documentDetails.getFileName());
            document.setFileSize(documentDetails.getFileSize());
            document.setLocation(documentDetails.getLocation());
            document.setAutoDelete(documentDetails.isAutoDelete());
            document.setAttachedTo(documentDetails.getAttachedTo());
            document.setLabels(documentDetails.getLabels());
            return documentRepository.save(document);
        });
    }

    public boolean deleteDocument(Integer id) {
        return documentRepository.findById(id).map(document -> {
            documentRepository.delete(document);
            return true;
        }).orElse(false);
    }
}
