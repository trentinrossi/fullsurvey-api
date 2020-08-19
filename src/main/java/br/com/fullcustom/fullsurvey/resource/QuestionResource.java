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

import br.com.fullcustom.fullsurvey.model.Question;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;
import br.com.fullcustom.fullsurvey.service.impl.QuestionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: realizar ajustes
@RestController
@RequestMapping(path = "/v1/rest/question", produces = "application/json")
@Api(value = "Question", 
     description = "Perguntas", 
     tags = "Question")
public class QuestionResource {

    @Autowired
    private QuestionServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as perguntas")
    public ResponseEntity<Page<QuestionDTO>> findAll(Pageable page) {
        Page<QuestionDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma pergunta conforme o id passado na requisição")
    public ResponseEntity<Question> findById(@PathVariable UUID id) {
        Question res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma nova pergunta")
    public ResponseEntity<Void> insert(@Valid @RequestBody QuestionDTO entity) {
        entity.setId(null);
        QuestionDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma pergunta")
    public ResponseEntity<QuestionDTO> update(@PathVariable UUID id, @Valid @RequestBody QuestionDTO entity) {
        entity.setId(id);
        QuestionDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma pergunta")
    public ResponseEntity<QuestionDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}