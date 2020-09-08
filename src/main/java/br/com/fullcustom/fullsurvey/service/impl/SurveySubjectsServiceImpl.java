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
import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.model.SurveySubjects;
import br.com.fullcustom.fullsurvey.repository.SurveySubjectsRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.ISurveySubjectsMapper;

@Service
public class SurveySubjectsServiceImpl implements IService<SurveySubjectsDTO, SurveySubjects> {

    @Autowired
    private SurveySubjectsRepository repository;

    @Autowired
    private ISurveySubjectsMapper mapper;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private SurveyServiceImpl surveyService;

    @Override
    public SurveySubjectsDTO save(SurveySubjectsDTO entity) {
        return null;
    }

    @Override
    public Page<SurveySubjectsDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public SurveySubjects findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(SurveySubjects.class.getName() + " not Found by id: " + id));
    }

    @Override
    public SurveySubjectsDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(SurveySubjects.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    /**
     * Retorna todos os {@link Subject} não relacionados à pesquisa passada no
     * parâmetro (subjects disponíveis para seleção) filtrados por categoria
     *
     * @param id
     * @return {@link Subject}
     */
    public Page<Subject> findAvaiableSubjectsBySurveyIdAndCategoryId(UUID id, UUID categoryId) {

        List<Subject> list = this.repository.findAvaiableSubjectsByCategoryId(id, categoryId);
        return new PageImpl<Subject>(list);
    }

    /**
     * Retorna todos os {@link Subject} não relacionados à pesquisa passada no
     * parâmetro (subjects disponíveis para seleção)
     * 
     * @param id
     * @return {@link Subject}
     */
    public Page<Subject> findAvaiableSubjectsBySurveyId(UUID id) {

        List<Subject> list = this.repository.findAvaiableSubjects(id);
        return new PageImpl<Subject>(list);
    }

    /**
     * Retorna todos os {@link Subject} relacionados da pesquisa passada no
     * parâmetro
     * 
     * @param id
     * @return {@link Subject}
     */
    public Page<Subject> findSubjectsBySurveyId(UUID id) {

        List<SurveySubjects> list = this.repository.findBySurveyId(id);
        List<Subject> subjects = new ArrayList<Subject>();

        for (int i = 0; i < list.size(); i++) {
            Subject sub = this.subjectService.findById(list.get(i).getSubject().getId());
            subjects.add(sub);
        }

        return new PageImpl<Subject>(subjects);
    }

    /**
     * Adiciona um ou mais assuntos em uma pesquisa
     * 
     * @param dto
     */
    public Page<Subject> addSubjectsToSurvey(SurveySubjectsDTO dto) {

        for (int i = 0; i < dto.getSubjects().size(); i++) {
            Subject subject = subjectService.findById(dto.getSubjects().get(i));
            Survey survey = surveyService.findById(dto.getSurveyId());

            SurveySubjects surveySubjectStored = repository.findBySurveyIdAndSubjectId(survey.getId(), subject.getId());
            if (surveySubjectStored == null) {
                SurveySubjects surveySubject = new SurveySubjects();
                surveySubject.setSurvey(survey);
                surveySubject.setSubject(subject);
                repository.save(surveySubject);
            }
        }
        return findSubjectsBySurveyId(dto.getSurveyId());
    }

    /**
     * Remove um ou mais assuntos de uma pesquisa
     * 
     * @param dto
     */
    public void deleteSubjectsFromSurvey(SurveySubjectsDTO dto) {
        for (int i = 0; i < dto.getSubjects().size(); i++) {
            Subject subject = subjectService.findById(dto.getSubjects().get(i));
            Survey survey = surveyService.findById(dto.getSurveyId());

            SurveySubjects surveySubjectStored = repository.findBySurveyIdAndSubjectId(survey.getId(), subject.getId());
            if (surveySubjectStored != null) {
                repository.deleteById(surveySubjectStored.getId());
            }
        }
    }
}