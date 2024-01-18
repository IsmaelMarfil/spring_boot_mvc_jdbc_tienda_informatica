package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ComercialDaoImpl  implements ComercialDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Comercial comercial) {

        jdbcTemplate.update("INSERT INTO comercial (nombre, apellido1, apellido2, comisión) VALUES (?, ?, ?, ?)",comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComisión());

    }

    /**
     * Devuelve lista con todos loa comerciales.
     */
    @Override
    public List<Comercial> getAll() {

        List<Comercial> listCom = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getFloat("comisión"))
        );

        return listCom;

    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Comercial> find(int id) {

        Comercial com =  jdbcTemplate
                .queryForObject("SELECT * FROM comercial WHERE id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"),rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getFloat("comisión") )
                        , id
                );

        if (com != null) return Optional.of(com);
        else return Optional.empty();

    }


    /**
     * Actualiza comercial con campos del bean comercial según ID del mismo.
     */
    @Override
    public void update(Comercial comercial) {

        int rows = jdbcTemplate.update("UPDATE comercial SET nombre = ?  WHERE id = ?", comercial.getNombre(), comercial.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }

    /**
     * Borra comercial con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }

}
