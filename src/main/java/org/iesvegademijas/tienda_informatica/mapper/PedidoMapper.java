package org.iesvegademijas.tienda_informatica.mapper;

import org.iesvegademijas.tienda_informatica.dto.PedidoFormDTO;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    //@Mapping(source = "idCliente", target = "idCliente")
    //@Mapping(source = "idComercial", target = "idComercial")
    //public PedidoFormDTO pedidoAPedidoFormDTO(Pedido pedido);

    //@Mapping(source = "idCliente", target = "idCliente")
    //@Mapping(source = "idComercial", target = "idCliente")
    //public Pedido pedidoFormDTOAPedido(PedidoFormDTO pedidoFormDTO);

}