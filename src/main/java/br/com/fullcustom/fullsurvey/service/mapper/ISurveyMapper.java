package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;

@Mapper(componentModel = "spring")
public interface ISurveyMapper {

    SurveyDTO toDto(Survey entity);

    Survey fromDto(SurveyDTO dto);
}