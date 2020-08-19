package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.repository.CustomerRepository;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveyMapper;

@Component
public class SurveyMapperImpl implements ISurveyMapper {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public SurveyDTO toDto(Survey entity) {

        if (entity == null) {
            return null;
        }

        SurveyDTO dto = new SurveyDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setName(entity.getName());
        dto.setInitialDate(entity.getInitialDate());
        dto.setFinalDate(entity.getFinalDate());
        dto.setInstructorName(entity.getInstructorName());
        dto.setEvaluatorName(entity.getEvaluatorName());
        dto.setDescription(entity.getDescription());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setObjective(entity.getObjective());
        dto.setResponseTime(entity.getResponseTime());
        dto.setAnonymous(entity.isAnonymous());
        dto.setAnswerLink(entity.getAnswerLink());
        dto.setTitleIcon(entity.getTitleIcon());

        return dto;
    }

    @Override
    public Survey fromDto(SurveyDTO dto) {
        if (dto == null) {
            return null;
        }

        Survey entity = new Survey();
        entity.setId(dto.getId());

        try {
            Customer c = customerRepository.findById(dto.getCustomerId()).get();
            entity.setCustomer(c);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Customer not found with id: " + dto.getCustomerId());
        }

        entity.setName(dto.getName());
        entity.setInitialDate(dto.getInitialDate());
        entity.setFinalDate(dto.getFinalDate());
        entity.setInstructorName(dto.getInstructorName());
        entity.setEvaluatorName(dto.getEvaluatorName());
        entity.setDescription(dto.getDescription());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setObjective(dto.getObjective());
        entity.setResponseTime(dto.getResponseTime());
        entity.setAnonymous(dto.isAnonymous());
        entity.setAnswerLink(dto.getAnswerLink());
        entity.setTitleIcon(dto.getTitleIcon());

        return entity;
    }

}