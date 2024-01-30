package org.iesvegademijas.tienda_informatica.servicio;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.iesvegademijas.tienda_informatica.dao.*;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pedido> listAll() {

        return pedidoDAO.getAll();

    }

    public Pedido one(Integer id) {
        Optional<Pedido> optPed= pedidoDAO.find(id);
        if (optPed.isPresent())
            return optPed.get();
        else
            return null;
    }

    public void newPedido(Pedido pedido) {

        pedidoDAO.create(pedido);

    }

    public void replacePedido(Pedido pedido) {

        pedidoDAO.update(pedido);

    }

    public void deletePedido(int id) {

        pedidoDAO.delete(id);

    }
    public List<Pedido> devolverListaPedidos(int id){
        List<Pedido> pedidosOrdenados = pedidoDAO.listaPedidosIdComercial(id).stream()
                .sorted(Comparator.comparing(pedido -> pedido.getTotal())).toList();
        return pedidosOrdenados ;
    }
    public double media(int id){
        OptionalDouble mediaOpt = pedidoDAO.listaPedidosIdComercial(id).stream()
                .mapToDouble(Pedido::getTotal).average();
        return mediaOpt.getAsDouble();
    }


}
