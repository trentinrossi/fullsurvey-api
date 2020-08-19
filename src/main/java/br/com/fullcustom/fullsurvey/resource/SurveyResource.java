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

import br.com.fullcustom.fullsurvey.model.Survey;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;
import br.com.fullcustom.fullsurvey.service.impl.SurveyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/survey", produces = "application/json")
@Api(value = "Survey", 
     description = "Pesquisa", 
     tags = "Survey")
public class SurveyResource {

    @Autowired
    private SurveyServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as pesquisas")
    public ResponseEntity<Page<SurveyDTO>> findAll(Pageable page) {
        Page<SurveyDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma pesquisa conforme o id enviado na requisição")
    public ResponseEntity<Survey> findById(@PathVariable UUID id) {
        Survey res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma nova pesquisa")
    public ResponseEntity<Void> insert(@Valid @RequestBody SurveyDTO entity) {
        entity.setId(null);
        SurveyDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma pesquisa")
    public ResponseEntity<SurveyDTO> update(@PathVariable UUID id, @Valid @RequestBody SurveyDTO entity) {
        entity.setId(id);
        SurveyDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma pesquisa")
    public ResponseEntity<SurveyDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}