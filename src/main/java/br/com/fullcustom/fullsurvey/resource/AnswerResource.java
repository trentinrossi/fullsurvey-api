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

import br.com.fullcustom.fullsurvey.model.Answer;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;
import br.com.fullcustom.fullsurvey.service.impl.AnswerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: realizar ajustes
@RestController
@RequestMapping(path = "/v1/rest/answer", produces = "application/json")
@Api(value = "Answer", 
     description = "Respostas das pesquisas", 
     tags = "Answer")
public class AnswerResource {

    @Autowired
    private AnswerServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as respostas que foram registradas pelos respondentes")
    public ResponseEntity<Page<AnswerDTO>> findAll(Pageable page) {
        Page<AnswerDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma resposta conforme o id enviado na requisição")
    public ResponseEntity<Answer> findById(@PathVariable UUID id) {
        Answer res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma nova resposta")
    public ResponseEntity<Void> insert(@Valid @RequestBody AnswerDTO entity) {
        entity.setId(null);
        AnswerDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma resposta")
    public ResponseEntity<AnswerDTO> update(@PathVariable UUID id,
            @Valid @RequestBody AnswerDTO entity) {
        entity.setId(id);
        AnswerDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma resposta")
    public ResponseEntity<AnswerDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}