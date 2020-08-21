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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fullcustom.fullsurvey.model.Respondent;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;
import br.com.fullcustom.fullsurvey.service.impl.RespondentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/respondent", produces = "application/json")
@Api(value = "Respondent", description = "Respondente", tags = "Respondent")
public class RespondentResource {

    @Autowired
    private RespondentServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os respondentes")
    public ResponseEntity<Page<RespondentDTO>> findAll(Pageable page,
            @RequestParam(value = "globalFilter", defaultValue = "") String globalFilter) {
        Page<RespondentDTO> list = service.findAll(page, globalFilter);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um respondente conforme o id enviado na requisição")
    public ResponseEntity<Respondent> findById(@PathVariable UUID id) {
        Respondent res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona um novo respondente")
    public ResponseEntity<Void> insert(@Valid @RequestBody RespondentDTO entity) {
        entity.setId(null);
        RespondentDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza um respondente")
    public ResponseEntity<RespondentDTO> update(@PathVariable UUID id, @Valid @RequestBody RespondentDTO entity) {
        entity.setId(id);
        RespondentDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um respondente")
    public ResponseEntity<RespondentDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}