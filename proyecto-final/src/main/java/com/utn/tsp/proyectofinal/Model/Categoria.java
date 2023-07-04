package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIA")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private String descripcion;

}
