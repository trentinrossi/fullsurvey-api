package br.com.fullcustom.fullsurvey.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fullcustom.fullsurvey.model.Configuration;
import br.com.fullcustom.fullsurvey.repository.ConfigurationRepository;
import br.com.fullcustom.fullsurvey.service.IService;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IConfigurationMapper;

@Service
public class ConfigurationServiceImpl implements IService<ConfigurationDTO, Configuration> {

    @Autowired
    private ConfigurationRepository repository;

    @Autowired
    private IConfigurationMapper mapper;

    @Override
    public ConfigurationDTO save(ConfigurationDTO entity) {
        Configuration Configuration = new Configuration();
        if (entity.getId() != null) {
            Configuration = mapper.fromDto(findByIdDto(entity.getId()));
        }
        Configuration = mapper.fromDto(entity);
        return mapper.toDto(repository.save(Configuration));
    }

    @Override
    public Page<ConfigurationDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    public Configuration findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Configuration.class.getName() + " not Found by id: " + id));
    }

    @Override
    public ConfigurationDTO findByIdDto(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new ObjectNotFoundException(Configuration.class.getName() + " not Found by id: " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}