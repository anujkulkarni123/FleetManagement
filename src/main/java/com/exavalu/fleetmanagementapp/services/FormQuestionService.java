package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.FormQuestion;
import com.exavalu.fleetmanagementapp.repositories.FormQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormQuestionService {

    @Autowired
    private FormQuestionRepository formQuestionRepository;

    public List<FormQuestion> getAllFormQuestions() {
        return formQuestionRepository.findAll();
    }

    public Optional<FormQuestion> getFormQuestionById(Integer id) {
        return formQuestionRepository.findById(id);
    }

    public FormQuestion createFormQuestion(FormQuestion formQuestion) {
        return formQuestionRepository.save(formQuestion);
    }

    public Optional<FormQuestion> updateFormQuestion(Integer id, FormQuestion formQuestionDetails) {
        return formQuestionRepository.findById(id).map(formQuestion -> {
            formQuestion.setForm(formQuestionDetails.getForm());
            formQuestion.setFormQuestions(formQuestionDetails.getFormQuestions());
            return formQuestionRepository.save(formQuestion);
        });
    }

    public boolean deleteFormQuestion(Integer id) {
        return formQuestionRepository.findById(id).map(formQuestion -> {
            formQuestionRepository.delete(formQuestion);
            return true;
        }).orElse(false);
    }
}
