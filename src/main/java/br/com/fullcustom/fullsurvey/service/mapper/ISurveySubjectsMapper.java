package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.SurveySubjects;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;

public interface ISurveySubjectsMapper {

    SurveySubjectsDTO toDto(SurveySubjects entity);

    // SurveySubjects fromDto(SurveySubjectsDTO dto);
}