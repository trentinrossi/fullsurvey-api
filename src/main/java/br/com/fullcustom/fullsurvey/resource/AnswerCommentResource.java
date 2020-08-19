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

import br.com.fullcustom.fullsurvey.model.AnswerComment;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;
import br.com.fullcustom.fullsurvey.service.impl.AnswerCommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: realizar ajustes
@RestController
@RequestMapping(path = "/v1/rest/answerComment", produces = "application/json")
@Api(value = "AnswerComment", 
     description = "Comentários registrados em uma pesquisa", 
     tags = "Answer Comment")
public class AnswerCommentResource {

    @Autowired
    private AnswerCommentServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os comentários registrados")
    public ResponseEntity<Page<AnswerCommentDTO>> findAll(Pageable page) {
        Page<AnswerCommentDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um comentário registrado conforme o Id enviado na requisição")
    public ResponseEntity<AnswerComment> findById(@PathVariable UUID id) {
        AnswerComment res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona um novo registro contendo os comentários de uma pesquisa")
    public ResponseEntity<Void> insert(@Valid @RequestBody AnswerCommentDTO entity) {
        entity.setId(null);
        AnswerCommentDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza um registro contendo os comentários de uma pesquisa")
    public ResponseEntity<AnswerCommentDTO> update(@PathVariable UUID id, @Valid @RequestBody AnswerCommentDTO entity) {
        entity.setId(id);
        AnswerCommentDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um registro contendo os comentários de uma pesquisa")
    public ResponseEntity<AnswerCommentDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}