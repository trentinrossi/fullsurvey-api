package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Customer;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer fromDto(CustomerDTO dto);
}