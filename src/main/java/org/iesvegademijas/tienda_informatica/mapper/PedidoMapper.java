package org.iesvegademijas.tienda_informatica.mapper;

import org.iesvegademijas.tienda_informatica.dto.ComercialDTO;
import org.iesvegademijas.tienda_informatica.dto.PedidoFormDTO;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mapping(source="idCliente", target = "idCliente")
    PedidoFormDTO pedidoaPedidoDTOCliente(Pedido pedido);
    @Mapping(source="idComercial", target = "idComercial")
    PedidoFormDTO pedidoaPedidoDtoComercial(Pedido pedido);


}