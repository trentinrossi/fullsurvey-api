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

import br.com.fullcustom.fullsurvey.model.AnswerCheckboxSelected;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;
import br.com.fullcustom.fullsurvey.service.impl.AnswerCheckboxSelectedServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Acredito que não precisa deste resource pq poderá ser adicionada as opções selecionadas nos checkboxes diretamente no resource de Answer
@RestController
@RequestMapping(path = "/v1/rest/answerCheckboxSelected", produces = "application/json")
@Api(value = "AnswerCheckboxSelected", 
     description = "Opções escolhidas pelo respondente quando a resposta for do tipo Checkbox (multipla escolha)", 
     tags = "Answer Checkbox Selected")
public class AnswerCheckboxSelectedResource {

    @Autowired
    private AnswerCheckboxSelectedServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as opções que foram selecionadas")
    public ResponseEntity<Page<AnswerCheckboxSelectedDTO>> findAll(Pageable page) {
        Page<AnswerCheckboxSelectedDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna as opções selecionadas conforme o id enviado na requisição")
    public ResponseEntity<AnswerCheckboxSelected> findById(@PathVariable UUID id) {
        AnswerCheckboxSelected res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma opção que foi selecionada")
    public ResponseEntity<Void> insert(@Valid @RequestBody AnswerCheckboxSelectedDTO entity) {
        entity.setId(null);
        AnswerCheckboxSelectedDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma opção que foi selecionada")
    public ResponseEntity<AnswerCheckboxSelectedDTO> update(@PathVariable UUID id,
            @Valid @RequestBody AnswerCheckboxSelectedDTO entity) {
        entity.setId(id);
        AnswerCheckboxSelectedDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma opção que foi selecionada")
    public ResponseEntity<AnswerCheckboxSelectedDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}