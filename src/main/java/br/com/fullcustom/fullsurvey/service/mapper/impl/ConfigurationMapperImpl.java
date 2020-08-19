package br.com.fullcustom.fullsurvey.service.mapper.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Configuration;
import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.repository.CustomerRepository;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.mapper.IConfigurationMapper;

@Component
public class ConfigurationMapperImpl implements IConfigurationMapper {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ConfigurationDTO toDto(Configuration entity) {

        if (entity == null) {
            return null;
        }

        ConfigurationDTO dto = new ConfigurationDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer().getId());

        return dto;
    }

    @Override
    public Configuration fromDto(ConfigurationDTO dto) {
        if (dto == null) {
            return null;
        }

        Configuration entity = new Configuration();
        entity.setId(dto.getId());

        try {
            Customer c = customerRepository.findById(dto.getCustomerId()).get();
            entity.setCustomer(c);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Customer not found with id: " + dto.getCustomerId());
        }

        return entity;
    }

}