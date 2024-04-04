package org.iesvegademijas.tienda_informatica.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ClienteDAOImpl  implements ClienteDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Cliente cliente) {

        jdbcTemplate.update("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)",cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoría());

    }

    /**
     * Devuelve lista con todos loa comerciales.
     */
    @Override
    public List<Cliente> getAll() {

        List<Cliente> listCli = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"))
        );

        return listCli;

    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Cliente> find(int id) {

        Cliente cli =  jdbcTemplate
                .queryForObject("SELECT * FROM cliente c WHERE c.id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría") )
                        , id
                );

        if (cli != null) return Optional.of(cli);
        else return Optional.empty();

    }


    /**
     * Actualiza comercial con campos del bean comercial según ID del mismo.
     */
    @Override
    public void update(Cliente cliente) {

        int rows = jdbcTemplate.update("UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoría = ?  WHERE id = ?", cliente.getNombre(),cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoría(), cliente.getId());
        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");

    }

    /**
     * Borra comercial con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }

    @Override
    public List<Comercial> getAllByCliente(int id) {
        String sql = """
          SELECT DISTINCT c.*
          FROM cliente cl
          LEFT JOIN pedido p ON cl.id = p.id_cliente
          LEFT JOIN comercial c ON p.id_comercial = c.id
          WHERE cl.id = ?
          
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Comercial comercial = new Comercial();
            comercial.setId(rs.getInt("id"));
            comercial.setNombre(rs.getString("nombre"));
            comercial.setApellido1(rs.getString("apellido1"));
            comercial.setApellido2(rs.getString("apellido2"));
            comercial.setComisión(rs.getBigDecimal("comisión"));
            return comercial;
        }, id);

    }

    @Override
    public int conteoUltimoTrimestre(Cliente  cliente) {

        String sql = """
                SELECT COUNT(*)
                FROM pedido
                JOIN comercial  ON comercial.id = pedido.id_comercial
                WHERE id_cliente = ?
                AND fecha >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH)
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId());
    }

    @Override
    public int conteoUltimoSemestre(Cliente cliente) {

        String sql = """
                SELECT COUNT(*)
                FROM pedido
                JOIN comercial  ON comercial.id = pedido.id_comercial
                WHERE id_cliente = ?
                AND fecha >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId());
    }

    @Override
    public int conteoUltimoAnio(Cliente cliente) {

        String sql = """
                SELECT COUNT(*)
                FROM pedido
                JOIN comercial ON comercial.id = pedido.id_comercial
                WHERE id_cliente = ?
                AND fecha >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId());
    }

    @Override
    public int conteoUltimoLustro(Cliente cliente) {

        String sql = """
                SELECT COUNT(*)
                FROM pedido
                JOIN comercial ON comercial.id = pedido.id_comercial
                WHERE id_cliente = ?
                AND fecha >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR)
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId());
    }






}
