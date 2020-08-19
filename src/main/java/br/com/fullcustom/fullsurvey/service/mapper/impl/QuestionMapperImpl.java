package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.repository.SubjectRepository;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IQuestionMapper;

@Component
public class QuestionMapperImpl implements IQuestionMapper {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public QuestionDTO toDto(Question entity) {

        if (entity == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setSubjectId(entity.getSubject().getId());
        dto.setAnswerType(entity.getAnswerType());
        dto.setTitle(entity.getTitle());
        dto.setMandatory(entity.isMandatory());

        return dto;
    }

    @Override
    public Question fromDto(QuestionDTO dto) {
        if (dto == null) {
            return null;
        }

        Question entity = new Question();
        entity.setId(dto.getId());

        try {
            Subject c = subjectRepository.findById(dto.getSubjectId()).get();
            entity.setSubject(c);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Subject not found with id: " + dto.getSubjectId());
        }

        entity.setAnswerType(dto.getAnswerType());
        entity.setTitle(dto.getTitle());
        entity.setMandatory(dto.isMandatory());

        return entity;
    }

}