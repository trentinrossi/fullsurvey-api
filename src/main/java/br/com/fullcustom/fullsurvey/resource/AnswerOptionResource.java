package br.com.fullcustom.fullsurvey.resource;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;
import br.com.fullcustom.fullsurvey.service.impl.AnswerOptionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/answerOption", produces = "application/json")
@Api(value = "AnswerOption", 
     description = "Opções de resposta de uma pergunta", 
     tags = "Answer Option")
public class AnswerOptionResource {

    @Autowired
    private AnswerOptionServiceImpl service;

    @GetMapping(path = "/getAnswerOptionsByQuestion/{id}")
    @ApiOperation(value = "Retorna todas as opções de resposta de uma determinada pergunta")
    public ResponseEntity<Page<AnswerOptionDTO>> findAllByQuestionId(@PathVariable UUID id) {
        Page<AnswerOptionDTO> res = service.findAnswerOptionsByQuestionId(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/addAnswerOption")
    @ApiOperation(value = "Adiciona uma nova opção de resposta para uma determinada pergunta")
    public ResponseEntity<Void> insert(@Valid @RequestBody AnswerOptionDTO entity) {
        entity.setId(null);
        service.isSequenceExists(entity.getSequence());
        AnswerOptionDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/updateAnswerOption/{id}")
    @ApiOperation(value = "Atualiza uma opção de resposta de uma determinada pergunta")
    public ResponseEntity<AnswerOptionDTO> update(@PathVariable UUID id, @Valid @RequestBody AnswerOptionDTO entity) {
        entity.setId(id);
        AnswerOptionDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/deleteAnswerOption/{id}")
    @ApiOperation(value = "Remove uma opção de resposta de uma determinada pergunta")
    public ResponseEntity<AnswerOptionDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}