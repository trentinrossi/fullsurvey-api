package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.repository.AnswerRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerMapper;

// TODO: Realizar as tratativas para adicionar os tipos de respostas somente nas perguntas que são compatíveis ao tipo de resposta sendo enviado

@Service
public class AnswerServiceImpl implements IService<AnswerDTO, Answer> {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private IAnswerMapper mapper;

    @Override
    public AnswerDTO save(AnswerDTO entity) {
        Answer AnswersRespondent = new Answer();
        if (entity.getId() != null) {
            AnswersRespondent = mapper.fromDto(findByIdDto(entity.getId()));
        }
        AnswersRespondent = mapper.fromDto(entity);
        return mapper.toDto(repository.save(AnswersRespondent));
    }

    @Override
    public Page<AnswerDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Answer findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Answer.class.getName() + " not Found by id: " + id));
    }

    @Override
    public AnswerDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Answer.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}