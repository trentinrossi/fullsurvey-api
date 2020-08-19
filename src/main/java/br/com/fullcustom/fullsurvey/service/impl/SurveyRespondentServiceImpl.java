package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.SurveyRespondent;
import br.com.fullcustom.fullsurvey.repository.SurveyRespondentRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveyRespondentMapper;

@Service
public class SurveyRespondentServiceImpl implements IService<SurveyRespondentDTO, SurveyRespondent> {

    @Autowired
    private SurveyRespondentRepository repository;

    @Autowired
    private ISurveyRespondentMapper mapper;

    @Override
    public SurveyRespondentDTO save(SurveyRespondentDTO entity) {
        SurveyRespondent SurveyRespondentsRespondent = new SurveyRespondent();
        if (entity.getId() != null) {
            SurveyRespondentsRespondent = mapper.fromDto(findByIdDto(entity.getId()));
        }
        SurveyRespondentsRespondent = mapper.fromDto(entity);
        return mapper.toDto(repository.save(SurveyRespondentsRespondent));
    }

    @Override
    public Page<SurveyRespondentDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public SurveyRespondent findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(SurveyRespondent.class.getName() + " not Found by id: " + id));
    }

    @Override
    public SurveyRespondentDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(SurveyRespondent.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}