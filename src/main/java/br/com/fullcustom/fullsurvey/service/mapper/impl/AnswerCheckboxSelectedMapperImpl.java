package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.model.AnswerCheckboxSelected;
import br.com.fullcustom.fullsurvey.model.AnswerOption;
import br.com.fullcustom.fullsurvey.repository.AnswerOptionRepository;
import br.com.fullcustom.fullsurvey.repository.AnswerRepository;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerCheckboxSelectedMapper;

@Component
public class AnswerCheckboxSelectedMapperImpl implements IAnswerCheckboxSelectedMapper {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerOptionRepository answerOptionRepository;

    @Override
    public AnswerCheckboxSelectedDTO toDto(AnswerCheckboxSelected entity) {

        if (entity == null) {
            return null;
        }

        AnswerCheckboxSelectedDTO dto = new AnswerCheckboxSelectedDTO();
        dto.setId(entity.getId());
        dto.setAnswerId(entity.getAnswer().getId());
        dto.setAnswerOptionId(entity.getAnswerOption().getId());

        return dto;
    }

    @Override
    public AnswerCheckboxSelected fromDto(AnswerCheckboxSelectedDTO dto) {
        if (dto == null) {
            return null;
        }

        AnswerCheckboxSelected entity = new AnswerCheckboxSelected();
        entity.setId(dto.getId());

        try {
            Answer a = answerRepository.findById(dto.getAnswerId()).get();
            entity.setAnswer(a);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Answer not found with id: " + dto.getAnswerId());
        }

        try {
            AnswerOption a = answerOptionRepository.findById(dto.getAnswerOptionId()).get();
            entity.setAnswerOption(a);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("AnswerOption not found with id: " + dto.getAnswerOptionId());
        }

        return entity;
    }

}