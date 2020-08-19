package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.SurveySubjects;
import br.com.fullcustom.fullsurvey.repository.SubjectRepository;
import br.com.fullcustom.fullsurvey.repository.SurveyRepository;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveySubjectsMapper;

@Component
public class SurveySubjectsMapperImpl implements ISurveySubjectsMapper {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public SurveySubjectsDTO toDto(SurveySubjects entity) {

        if (entity == null) {
            return null;
        }

        SurveySubjectsDTO dto = new SurveySubjectsDTO();
        dto.setId(entity.getId());
        dto.setSurveyId(entity.getSurvey().getId());

        ArrayList<UUID> subjects = new ArrayList<UUID>();
        subjects.add(entity.getSubject().getId());
        dto.setSubjects(subjects);

        return dto;
    }

    // @Override
    // public SurveySubjects fromDto(SurveySubjectsDTO dto) {
    //     if (dto == null) {
    //         return null;
    //     }

    //     SurveySubjects entity = new SurveySubjects();
    //     entity.setId(dto.getId());

    //     try {
    //         Survey c = surveyRepository.findById(dto.getSurveyId()).get();
    //         entity.setSurvey(c);
    //     } catch (NoSuchElementException e) {
    //         throw new ObjectNotFoundException("Survey not found with id: " + dto.getSurveyId());
    //     }

    //     try {
    //         for (int i = 0; i < dto.getSubjects().size(); i++) {
    //             Subject c = subjectRepository.findById(dto.getSubjects().get(i)).get();
    //             entity.setSubject(c);
    //         }
    //     } catch (NoSuchElementException e) {
    //         throw new ObjectNotFoundException("Subject not found with id: " + dto.getSubjectId());
    //     }

    //     return entity;
    // }

}