package org.iesvegademijas.tienda_informatica.mapper;

import org.iesvegademijas.tienda_informatica.dto.ComercialDTO;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ComercialMapper {

    ComercialDTO comercialAComercialDTO(Comercial comercial);


}
