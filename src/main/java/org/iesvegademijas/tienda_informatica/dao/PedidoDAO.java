package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;

public interface PedidoDAO {

    public void create(Pedido pedido);

        public List<Pedido> getAll();
    public Optional<Pedido>  find(int id);

    public void update(Pedido pedido);

    public void delete(int id);

    public List<Pedido> listaPedidosIdComercial (int id);

}
