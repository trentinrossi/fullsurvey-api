package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.SurveyRespondent;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;

public interface ISurveyRespondentMapper {

    SurveyRespondentDTO toDto(SurveyRespondent entity);

    SurveyRespondent fromDto(SurveyRespondentDTO dto);
}