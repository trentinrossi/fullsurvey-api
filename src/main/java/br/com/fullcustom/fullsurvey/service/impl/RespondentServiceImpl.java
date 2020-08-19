package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.repository.RespondentRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IRespondentMapper;

@Service
public class RespondentServiceImpl implements IService<RespondentDTO, Respondent> {

    @Autowired
    private RespondentRepository repository;

    @Autowired
    private IRespondentMapper mapper;

    @Override
    public RespondentDTO save(RespondentDTO entity) {
        Respondent Respondent = new Respondent();
        if (entity.getId() != null) {
            Respondent = mapper.fromDto(findByIdDto(entity.getId()));
        }
        Respondent = mapper.fromDto(entity);
        return mapper.toDto(repository.save(Respondent));
    }

    @Override
    public Page<RespondentDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Respondent findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Respondent.class.getName() + " not Found by id: " + id));
    }

    @Override
    public RespondentDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Respondent.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}