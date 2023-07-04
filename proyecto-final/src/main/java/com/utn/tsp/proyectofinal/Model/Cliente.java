package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CLIENTE")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String nombre;
    private String apellido;
    private Long dni;
    private String domicilio;
    private Date fechaBaja;

}
