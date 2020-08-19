package br.com.fullcustom.fullsurvey.service.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.repository.SubjectRepository;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;
import br.com.fullcustom.fullsurvey.service.mapper.IRespondentMapper;

@Component
public class RespondentMapperImpl implements IRespondentMapper {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public RespondentDTO toDto(Respondent entity) {

        if (entity == null) {
            return null;
        }

        RespondentDTO dto = new RespondentDTO();
        BeanUtils.copyProperties(entity, dto);
        // dto.setId(entity.getId());
        // dto.setRespondentIdentifier(entity.getRespondentIdentifier());
        // dto.setRespondentType(entity.getRespondentType());
        // dto.setRegistration(entity.getRegistration());
        // dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Respondent fromDto(RespondentDTO dto) {
        if (dto == null) {
            return null;
        }

        Respondent entity = new Respondent();
        BeanUtils.copyProperties(dto, entity);

        return entity;
    }

}