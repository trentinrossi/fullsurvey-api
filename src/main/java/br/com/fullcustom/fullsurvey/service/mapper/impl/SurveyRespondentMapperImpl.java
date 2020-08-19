package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.model.SurveyRespondent;
import br.com.fullcustom.fullsurvey.repository.RespondentRepository;
import br.com.fullcustom.fullsurvey.repository.SurveyRepository;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveyRespondentMapper;

@Component
public class SurveyRespondentMapperImpl implements ISurveyRespondentMapper {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    RespondentRepository respondentRepository;

    @Override
    public SurveyRespondentDTO toDto(SurveyRespondent entity) {

        if (entity == null) {
            return null;
        }

        SurveyRespondentDTO dto = new SurveyRespondentDTO();
        dto.setId(entity.getId());
        dto.setSurveyId(entity.getSurvey().getId());
        dto.setRespondentId(entity.getRespondent().getId());
        dto.setAnswerStatus(entity.getAnswerStatus());
        dto.setLink(entity.getLink());

        return dto;
    }

    @Override
    public SurveyRespondent fromDto(SurveyRespondentDTO dto) {
        if (dto == null) {
            return null;
        }

        SurveyRespondent entity = new SurveyRespondent();
        entity.setId(dto.getId());

        try {
            Survey s = surveyRepository.findById(dto.getSurveyId()).get();
            entity.setSurvey(s);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Survey not found with id: " + dto.getSurveyId());
        }

        try {
            Respondent r = respondentRepository.findById(dto.getRespondentId()).get();
            entity.setRespondent(r);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Respondent not found with id: " + dto.getRespondentId());
        }

        entity.setAnswerStatus(dto.getAnswerStatus());
        entity.setLink(dto.getLink());

        return entity;
    }

}