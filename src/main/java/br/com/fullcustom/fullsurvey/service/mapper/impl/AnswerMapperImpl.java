package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.repository.QuestionRepository;
import br.com.fullcustom.fullsurvey.repository.RespondentRepository;
import br.com.fullcustom.fullsurvey.repository.SurveyRepository;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerMapper;

@Component
public class AnswerMapperImpl implements IAnswerMapper {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    RespondentRepository respondentRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public AnswerDTO toDto(Answer entity) {

        if (entity == null) {
            return null;
        }

        AnswerDTO dto = new AnswerDTO();
        dto.setId(entity.getId());
        dto.setSurveyId(entity.getSurvey().getId());
        dto.setRespondentId(entity.getRespondent().getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setAnswerText(entity.getAnswerText());
        dto.setAnswerNps(entity.getAnswerNps());
        dto.setAnswerRadio(entity.getAnswerRadio());

        return dto;
    }

    @Override
    public Answer fromDto(AnswerDTO dto) {
        if (dto == null) {
            return null;
        }

        Answer entity = new Answer();
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

        try {
            Question q = questionRepository.findById(dto.getQuestionId()).get();
            entity.setQuestion(q);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Question not found with id: " + dto.getQuestionId());
        }

        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());
        entity.setAnswerText(dto.getAnswerText());
        entity.setAnswerNps(dto.getAnswerNps());
        entity.setAnswerRadio(dto.getAnswerRadio());

        return entity;
    }

}