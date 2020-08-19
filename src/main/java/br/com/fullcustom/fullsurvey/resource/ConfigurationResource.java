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

import br.com.fullcustom.fullsurvey.model.Configuration;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;
import br.com.fullcustom.fullsurvey.service.impl.ConfigurationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/configuration", produces = "application/json")
@Api(value = "Configuration", 
     description = "Configurações", 
     tags = "Configuration")
public class ConfigurationResource {

    @Autowired
    private ConfigurationServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as configurações")
    public ResponseEntity<Page<ConfigurationDTO>> findAll(Pageable page) {
        Page<ConfigurationDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma configuração conforme o id enviado na requisição")
    public ResponseEntity<Configuration> findById(@PathVariable UUID id) {
        Configuration res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma nova configuração")
    public ResponseEntity<Void> insert(@Valid @RequestBody ConfigurationDTO entity) {
        entity.setId(null);
        ConfigurationDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma configuração")
    public ResponseEntity<ConfigurationDTO> update(@PathVariable UUID id, @Valid @RequestBody ConfigurationDTO entity) {
        entity.setId(id);
        ConfigurationDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma configuração")
    public ResponseEntity<ConfigurationDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}