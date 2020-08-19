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

import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;
import br.com.fullcustom.fullsurvey.service.impl.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Acredito que este endpoint deve ficar liberado somente para o Admin
@RestController
@RequestMapping(path = "/v1/rest/customer", produces = "application/json")
@Api(value = "Customer", 
     description = "Cliente", 
     tags = "Customer")
public class CustomerResource {

    @Autowired
    private CustomerServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os clientes")
    public ResponseEntity<Page<CustomerDTO>> findAll(Pageable page) {
        Page<CustomerDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um cliente pelo código")
    public ResponseEntity<Customer> findById(@PathVariable UUID id) {
        Customer res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona um novo cliente")
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerDTO entity) {
        entity.setId(null);
        CustomerDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza um cliente")
    public ResponseEntity<CustomerDTO> update(@PathVariable UUID id, @Valid @RequestBody CustomerDTO entity) {
        entity.setId(id);
        CustomerDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um cliente")
    public ResponseEntity<CustomerDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}