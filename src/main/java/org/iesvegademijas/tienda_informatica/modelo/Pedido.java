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
}

