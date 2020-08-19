package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.AnswerOption;
import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.repository.QuestionRepository;
import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerOptionMapper;

@Component
public class AnswerOptionMapperImpl implements IAnswerOptionMapper {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public AnswerOptionDTO toDto(AnswerOption entity) {

        if (entity == null) {
            return null;
        }

        AnswerOptionDTO dto = new AnswerOptionDTO();
        dto.setId(entity.getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setDescription(entity.getDescription());
        dto.setSequence(entity.getSequence());

        return dto;
    }

    @Override
    public AnswerOption fromDto(AnswerOptionDTO dto) {
        if (dto == null) {
            return null;
        }

        AnswerOption entity = new AnswerOption();
        entity.setId(dto.getId());

        try {
            Question c = questionRepository.findById(dto.getQuestionId()).get();
            entity.setQuestion(c);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Question not found with id: " + dto.getQuestionId());
        }

        entity.setDescription(dto.getDescription());
        entity.setSequence(dto.getSequence());

        return entity;
    }

}