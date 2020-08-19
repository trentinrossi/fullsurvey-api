package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.repository.SurveyRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveyMapper;

@Service
public class SurveyServiceImpl implements IService<SurveyDTO, Survey> {

    @Autowired
    private SurveyRepository repository;

    @Autowired
    private ISurveyMapper mapper;

    @Override
    public SurveyDTO save(SurveyDTO entity) {
        Survey survey = new Survey();
        if (entity.getId() != null) {
            survey = mapper.fromDto(findByIdDto(entity.getId()));
        }
        survey = mapper.fromDto(entity);
        return mapper.toDto(repository.save(survey));
    }

    @Override
    public Page<SurveyDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Survey findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Survey.class.getName() + " not Found by id: " + id));
    }

    @Override
    public SurveyDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Survey.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}