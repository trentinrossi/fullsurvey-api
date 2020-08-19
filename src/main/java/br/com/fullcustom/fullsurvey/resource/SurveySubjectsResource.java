package br.com.fullcustom.fullsurvey.resource;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;
import br.com.fullcustom.fullsurvey.service.impl.SurveySubjectsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/surveySubjects", produces = "application/json")
@Api(value = "Survey Subjects", 
     description = "Assuntos/domensões de uma pesquisa", 
     tags = "Survey Subjects")
public class SurveySubjectsResource {

    @Autowired
    private SurveySubjectsServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os relacionamentos de assuntos com pesquisas")
    public ResponseEntity<Page<SurveySubjectsDTO>> findAll(Pageable page) {
        Page<SurveySubjectsDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/getSurveySubjects/{id}")
    @ApiOperation(value = "Retorna todos os assuntos de uma determinada pesquisa")
    public ResponseEntity<Page<Subject>> findBySurveyId(@PathVariable UUID id) {
        Page<Subject> subjects = service.findSubjectsBySurveyId(id);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping(path = "/addSurveySubjects")
    @ApiOperation(value = "Adiciona um novo assunto a uma pesquisa")
    public ResponseEntity<Page<Subject>> insertSubjectToSurvey(@Valid @RequestBody SurveySubjectsDTO entity) {
        entity.setId(null);
        service.addSubjectsToSurvey(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/getSubjectsFromSurvey/{id}").buildAndExpand(entity.getSurveyId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/deleteSurveySubjetcs")
    @ApiOperation(value = "Remove um assunto de uma pesquisa")
    public ResponseEntity<SurveySubjectsDTO> delete(@Valid @RequestBody SurveySubjectsDTO entity) {
        service.deleteSubjectsFromSurvey(entity);
        return ResponseEntity.noContent().build();
    }
}