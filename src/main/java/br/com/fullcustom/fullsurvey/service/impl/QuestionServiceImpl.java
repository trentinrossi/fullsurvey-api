package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.repository.QuestionRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IQuestionMapper;

@Service
public class QuestionServiceImpl implements IService<QuestionDTO, Question> {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private IQuestionMapper mapper;

    @Override
    public QuestionDTO save(QuestionDTO entity) {
        Question Question = new Question();
        if (entity.getId() != null) {
            Question = mapper.fromDto(findByIdDto(entity.getId()));
        }
        Question = mapper.fromDto(entity);
        return mapper.toDto(repository.save(Question));
    }

    @Override
    public Page<QuestionDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Question findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Question.class.getName() + " not Found by id: " + id));
    }

    @Override
    public QuestionDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Question.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}