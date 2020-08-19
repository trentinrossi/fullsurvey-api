package br.com.fullcustom.fullsurvey.service.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;
import br.com.fullcustom.fullsurvey.service.mapper.ICustomerMapper;

@Component
public class CustomerMapperImpl implements ICustomerMapper {

    @Override
    public CustomerDTO toDto(Customer entity) {

        if (entity == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Customer fromDto(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }

}