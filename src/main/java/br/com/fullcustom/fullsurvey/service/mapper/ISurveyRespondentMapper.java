package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.SurveyRespondent;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;

@Mapper(componentModel = "spring")
public interface ISurveyRespondentMapper {

    SurveyRespondentDTO toDto(SurveyRespondent entity);

    SurveyRespondent fromDto(SurveyRespondentDTO dto);
}