package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;

@Mapper(componentModel = "spring")
public interface ISubjectMapper {

    SubjectDTO toDto(Subject entity);

    Subject fromDto(SubjectDTO dto);
}