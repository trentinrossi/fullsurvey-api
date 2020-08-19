package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.AnswerCheckboxSelected;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;

@Mapper(componentModel = "spring")
public interface IAnswerCheckboxSelectedMapper {

    AnswerCheckboxSelectedDTO toDto(AnswerCheckboxSelected entity);

    AnswerCheckboxSelected fromDto(AnswerCheckboxSelectedDTO dto);
}