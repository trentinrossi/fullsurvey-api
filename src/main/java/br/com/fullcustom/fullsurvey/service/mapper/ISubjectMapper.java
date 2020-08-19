package br.com.fullcustom.fullsurvey.service.mapper;

import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;

public interface ISubjectMapper {

    SubjectDTO toDto(Subject entity);

    Subject fromDto(SubjectDTO dto);
}