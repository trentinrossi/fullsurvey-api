package br.com.fullcustom.fullsurvey.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.AnswerOption;
import br.com.fullcustom.fullsurvey.repository.AnswerOptionRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.exceptions.SequenceAlreadyExistsException;
import br.com.fullcustom.fullsurvey.service.mapper.IAnswerOptionMapper;

@Service
public class AnswerOptionServiceImpl implements IService<AnswerOptionDTO, AnswerOption> {

    @Autowired
    private AnswerOptionRepository repository;

    @Autowired
    private IAnswerOptionMapper mapper;

    @Override
    public AnswerOptionDTO save(AnswerOptionDTO entity) {
        AnswerOption AnswerOption = new AnswerOption();
        if (entity.getId() != null) {
            AnswerOption = mapper.fromDto(findByIdDto(entity.getId()));
        }
        AnswerOption = mapper.fromDto(entity);
        return mapper.toDto(repository.save(AnswerOption));
    }

    @Override
    public Page<AnswerOptionDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public AnswerOption findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(AnswerOption.class.getName() + " not Found by id: " + id));
    }

    @Override
    public AnswerOptionDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(AnswerOption.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    /**
     * Retorna todos os {@link AnswerOption} relacionados da pergunta passada no
     * parâmetro
     * 
     * @param id
     * @return {@link AnswerOption}
     */
    public Page<AnswerOptionDTO> findAnswerOptionsByQuestionId(UUID id) {
        List<AnswerOption> answers = repository.findByQuestionId(id);
        List<AnswerOptionDTO> dtos = new ArrayList<>();

        for (int i = 0; i < answers.size(); i++) {
            dtos.add(this.mapper.toDto(answers.get(i)));
        }

        return new PageImpl<AnswerOptionDTO>(dtos);
    }

    /**
     * Valida se a sequencia da questão passada já existe no BD
     * 
     * @param sequence
     */
    public void isSequenceExists(int sequence) {
        if (repository.findBySequence(sequence) != null) {
            throw new SequenceAlreadyExistsException(
                    "Error to add Answer Option do Question: Sequence " + sequence + " already exists.");
        }
    }

}