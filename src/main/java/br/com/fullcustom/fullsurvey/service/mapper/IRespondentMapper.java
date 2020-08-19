package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;

@Mapper(componentModel = "spring")
public interface IRespondentMapper {

    RespondentDTO toDto(Respondent entity);

    Respondent fromDto(RespondentDTO dto);
}