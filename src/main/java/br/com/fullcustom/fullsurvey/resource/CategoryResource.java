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

import br.com.fullcustom.fullsurvey.model.Category;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;
import br.com.fullcustom.fullsurvey.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/v1/rest/category", produces = "application/json")
@Api(value = "Category", 
     description = "Categorias", 
     tags = "Category")
public class CategoryResource {

    @Autowired
    private CategoryServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Retorna todas as categorias")
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable page) {
        Page<CategoryDTO> list = service.findAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma categoria conforme o id enviado na requisição")
    public ResponseEntity<Category> findById(@PathVariable UUID id) {
        Category res = service.findById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ApiOperation(value = "Adiciona uma nova categoria")
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO entity) {
        entity.setId(null);
        CategoryDTO dto = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza uma categoria")
    public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @Valid @RequestBody CategoryDTO entity) {
        entity.setId(id);
        CategoryDTO objSaved = service.save(entity);
        return ResponseEntity.ok(objSaved);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma categoria")
    public ResponseEntity<CategoryDTO> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}