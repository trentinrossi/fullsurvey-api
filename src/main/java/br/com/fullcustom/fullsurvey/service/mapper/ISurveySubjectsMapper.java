package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.SurveySubjects;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;

@Mapper(componentModel = "spring")
public interface ISurveySubjectsMapper {

    SurveySubjectsDTO toDto(SurveySubjects entity);

    // SurveySubjects fromDto(SurveySubjectsDTO dto);
}