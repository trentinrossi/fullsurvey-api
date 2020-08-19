package br.com.fullcustom.fullsurvey.service.mapper;


import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;

public interface IRespondentMapper {

    RespondentDTO toDto(Respondent entity);

    Respondent fromDto(RespondentDTO dto);
}