package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Forms;
import com.exavalu.fleetmanagementapp.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormRepository formsRepository;

    public List<Forms> getAllForms() {
        return formsRepository.findAll();
    }

    public Optional<Forms> getFormById(Integer id) {
        return formsRepository.findById(id);
    }

    public Forms createForm(Forms form) {
        return formsRepository.save(form);
    }

    public Optional<Forms> updateForm(Integer id, Forms formDetails) {
        return formsRepository.findById(id).map(form -> {
            form.setInspection(formDetails.getInspection());
            form.setFormType(formDetails.getFormType());
            return formsRepository.save(form);
        });
    }

    public boolean deleteForm(Integer id) {
        return formsRepository.findById(id).map(form -> {
            formsRepository.delete(form);
            return true;
        }).orElse(false);
    }
}
