package org.iesvegademijas.tienda_informatica.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int id;
    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No puede estar en blanco")
    @Length(min = 1 , max = 30, message = "El máximo son 30 caracteres")
    private String nombre;
    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No puede estar en blanco")
    @Length(min = 1 , max = 30, message = "El máximo son 30 caracteres")
    private String apellido1;
    private String apellido2;
    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No puede estar en blanco")
    @Length(min = 1 , max = 30, message = "El máximo son 30 caracteres")
    private String ciudad;
    @DecimalMin(value = "100.0", message = "Debe ser mayor a  100.0")
    @DecimalMax(value = "1000.0", message = "Debe ser menor a  1000.0")
    private int categoría;
}



