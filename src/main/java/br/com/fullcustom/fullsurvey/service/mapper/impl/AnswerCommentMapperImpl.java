package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.model.AnswerComment;
import br.com.fullcustom.fullsurvey.repository.QuestionRepository;
import br.com.fullcustom.fullsurvey.repository.RespondentRepository;
import br.com.fullcustom.fullsurvey.repository.SurveyRepository;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerCommentMapper;

@Component
public class AnswerCommentMapperImpl implements IAnswerCommentMapper {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    RespondentRepository respondentRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public AnswerCommentDTO toDto(AnswerComment entity) {

        if (entity == null) {
            return null;
        }

        AnswerCommentDTO dto = new AnswerCommentDTO();
        dto.setId(entity.getId());
        dto.setSurveyId(entity.getSurvey().getId());
        dto.setRespondentId(entity.getRespondent().getId());
        dto.setPositiveComment(entity.getPositiveComment());
        dto.setNegativeComment(entity.getNegativeComment());

        return dto;
    }

    @Override
    public AnswerComment fromDto(AnswerCommentDTO dto) {
        if (dto == null) {
            return null;
        }

        AnswerComment entity = new AnswerComment();
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

        entity.setPositiveComment(dto.getPositiveComment());
        entity.setNegativeComment(dto.getNegativeComment());
        return entity;
    }

}