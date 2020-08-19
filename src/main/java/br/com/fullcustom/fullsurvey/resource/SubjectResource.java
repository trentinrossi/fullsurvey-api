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

import br.com.fullcustom.fullsurvey.model.Subject;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;
import br.com.fullcustom.fullsurvey.service.impl.SubjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/subject", produces = "application/json")
@Api(value = "Subject", 
     description = "Assuntos/dimensões", 
     tags = "Subject")
public class SubjectResource {

    @Autowired
    private SubjectServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os assuntos")
    public ResponseEntity<Page<SubjectDTO>> findAll(Pageable page) {
        Page<SubjectDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um assunto conforme o id enviado na requisição")
    public ResponseEntity<Subject> findById(@PathVariable UUID id) {
        Subject res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona um novo assunto")
    public ResponseEntity<Void> insert(@Valid @RequestBody SubjectDTO entity) {
        entity.setId(null);
        SubjectDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza um assunto")
    public ResponseEntity<SubjectDTO> update(@PathVariable UUID id, @Valid @RequestBody SubjectDTO entity) {
        entity.setId(id);
        SubjectDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um assunto")
    public ResponseEntity<SubjectDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}