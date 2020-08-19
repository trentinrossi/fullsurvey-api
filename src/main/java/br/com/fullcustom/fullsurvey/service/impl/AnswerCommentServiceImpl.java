package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.AnswerComment;
import br.com.fullcustom.fullsurvey.repository.AnswerCommentRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerCommentMapper;

@Service
public class AnswerCommentServiceImpl implements IService<AnswerCommentDTO, AnswerComment> {

    @Autowired
    private AnswerCommentRepository repository;

    @Autowired
    private IAnswerCommentMapper mapper;

    @Override
    public AnswerCommentDTO save(AnswerCommentDTO entity) {
        AnswerComment AnswerCommentsRespondent = new AnswerComment();
        if (entity.getId() != null) {
            AnswerCommentsRespondent = mapper.fromDto(findByIdDto(entity.getId()));
        }
        AnswerCommentsRespondent = mapper.fromDto(entity);
        return mapper.toDto(repository.save(AnswerCommentsRespondent));
    }

    @Override
    public Page<AnswerCommentDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public AnswerComment findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(AnswerComment.class.getName() + " not Found by id: " + id));
    }

    @Override
    public AnswerCommentDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(AnswerComment.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}