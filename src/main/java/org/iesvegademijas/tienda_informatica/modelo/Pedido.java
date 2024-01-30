package org.iesvegademijas.tienda_informatica.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private double total;
    private Date fecha;
    private int idCliente;
    private int idComercial;
    private Cliente cliente;
    private Comercial comercial;

    public Pedido(int id, double total, Date fecha, int idCliente, int idComercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idComercial = idComercial;
    }


    public Pedido(int id, double total, java.sql.Date fecha, Cliente cliente, Comercial comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }
}

