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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fullcustom.fullsurvey.model.SurveyRespondent;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;
import br.com.fullcustom.fullsurvey.service.impl.SurveyRespondentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: realizar ajustes
@RestController
@RequestMapping(path = "/v1/rest/surveyRespondent", produces = "application/json")
@Api(value = "SurveyRespondent", 
     description = "Respondentes de uma pesquisa", 
     tags = "Survey Respondent")
public class SurveyRespondentResource {

    @Autowired
    private SurveyRespondentServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os respondentes vinculados as pesquisas")
    public ResponseEntity<Page<SurveyRespondentDTO>> findAll(Pageable page) {
        Page<SurveyRespondentDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um registro de vinculação")
    public ResponseEntity<SurveyRespondent> findById(@PathVariable UUID id) {
        SurveyRespondent res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona um novo respondente")
    public ResponseEntity<Void> insert(@Valid @RequestBody SurveyRespondentDTO entity) {
        entity.setId(null);
        SurveyRespondentDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza um respondente")
    public ResponseEntity<SurveyRespondentDTO> update(@PathVariable UUID id,
            @Valid @RequestBody SurveyRespondentDTO entity) {
        entity.setId(id);
        SurveyRespondentDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um respondente")
    public ResponseEntity<SurveyRespondentDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}