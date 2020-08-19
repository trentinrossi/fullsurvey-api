package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.AnswerCheckboxSelected;
import br.com.fullcustom.fullsurvey.repository.AnswerCheckboxSelectedRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerCheckboxSelectedMapper;

@Service
public class AnswerCheckboxSelectedServiceImpl implements IService<AnswerCheckboxSelectedDTO, AnswerCheckboxSelected> {

    @Autowired
    private AnswerCheckboxSelectedRepository repository;

    @Autowired
    private IAnswerCheckboxSelectedMapper mapper;

    @Override
    public AnswerCheckboxSelectedDTO save(AnswerCheckboxSelectedDTO entity) {
        AnswerCheckboxSelected AnswerCheckboxSelected = new AnswerCheckboxSelected();
        if (entity.getId() != null) {
            AnswerCheckboxSelected = mapper.fromDto(findByIdDto(entity.getId()));
        }
        AnswerCheckboxSelected = mapper.fromDto(entity);
        return mapper.toDto(repository.save(AnswerCheckboxSelected));
    }

    @Override
    public Page<AnswerCheckboxSelectedDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public AnswerCheckboxSelected findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(AnswerCheckboxSelected.class.getName() + " not Found by id: " + id));
    }

    @Override
    public AnswerCheckboxSelectedDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ObjectNotFoundException(AnswerCheckboxSelected.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}