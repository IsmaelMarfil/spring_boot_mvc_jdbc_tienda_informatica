package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PedidoDAOImpl  implements PedidoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Pedido pedido) {

        jdbcTemplate.update("INSERT INTO pedido (total, fecha, id_cliente, id_comercial) VALUES (?, ?, ?, ?)",pedido.getTotal(), pedido.getFecha(), pedido.getIdCliente(), pedido.getIdComercial());

    }

    /**
     * Devuelve lista con todos loa comerciales.
     */
    @Override
    public List<Pedido> getAll() {

        List<Pedido> listPed = jdbcTemplate.query(
                """ 
                        SELECT * 
                        FROM pedido p
                        LEFT JOIN cliente c ON p.id_cliente = c.id
                        LEFT JOIN comercial co ON p.id_comercial = co.id
                        """,
                (rs, rowNum) -> new Pedido(rs.getInt("id"),rs.getDouble("total"), rs.getDate("fecha"), rs.getInt("id_cliente"), rs.getInt("id_comercial"))
        );

        return listPed;

    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Pedido> find(int id) {

        Pedido ped =  jdbcTemplate
                .queryForObject("SELECT * FROM pedido WHERE id = ?"
                        , (rs, rowNum) -> new Pedido(rs.getInt("id"),rs.getDouble("total"), rs.getDate("fecha"), rs.getInt("id_Cliente"), rs.getInt("id_comercial") )
                        , id
                );

        if (ped != null) return Optional.of(ped);
        else return Optional.empty();

    }


    /**
     * Actualiza comercial con campos del bean comercial según ID del mismo.
     */
    @Override
    public void update(Pedido pedido) {

        int rows = jdbcTemplate.update("UPDATE pedido SET total = ?  WHERE id = ?", pedido.getTotal(), pedido.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }

    /**
     * Borra comercial con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }
    @Override
    public List<Pedido> listaPedidosIdComercial(int id){
        List<Pedido> pedidos = jdbcTemplate.query("""
                SELECT * FROM pedido p left join cliente c on p.id_cliente = c.id left join comercial co on p.id_comercial = co.id where p.id_comercial = ?
                """, (rs, rowNum) -> UtilDAO.newPedido(rs), id);
        return pedidos;
    }

}
