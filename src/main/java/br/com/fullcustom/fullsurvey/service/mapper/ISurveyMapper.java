package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;

public interface ISurveyMapper {

    SurveyDTO toDto(Survey entity);

    Survey fromDto(SurveyDTO dto);
}