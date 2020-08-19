package br.com.fullcustom.fullsurvey.service.mapper;

import org.mapstruct.Mapper;

import br.com.fullcustom.fullsurvey.model.Configuration;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;

@Mapper(componentModel = "spring")
public interface IConfigurationMapper {

    ConfigurationDTO toDto(Configuration entity);

    Configuration fromDto(ConfigurationDTO dto);
}