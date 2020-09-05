package br.com.fullcustom.fullsurvey.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.repository.SubjectRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISubjectMapper;

@Service
public class SubjectServiceImpl implements IService<SubjectDTO, Subject> {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private ISubjectMapper mapper;

    @Override
    public SubjectDTO save(SubjectDTO entity) {
        Subject subject = new Subject();
        if (entity.getId() != null) {
            subject = mapper.fromDto(findByIdDto(entity.getId()));
        }
        subject = mapper.fromDto(entity);
        return mapper.toDto(repository.save(subject));
    }

    @Override
    public Page<SubjectDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    public Page<Subject> findAllNoDto(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Subject findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Subject.class.getName() + " not Found by id: " + id));
    }

    @Override
    public SubjectDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Subject.class.getName() + " not Found by id: " + id));
    }    

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    /**
     * Retorna todos os {@link AnswerOptionDTO} relacionados da categoria passada no
     * parâmetro
     * 
     * @param id
     * @return {@link SubjectDTO}
     */
    public Page<SubjectDTO> findSubjectsByCategoryId(UUID id) {
        List<Subject> subjects = repository.findByCategoryId(id);
        List<SubjectDTO> dtos = new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {
            dtos.add(this.mapper.toDto(subjects.get(i)));
        }

        return new PageImpl<SubjectDTO>(dtos);
    }

}