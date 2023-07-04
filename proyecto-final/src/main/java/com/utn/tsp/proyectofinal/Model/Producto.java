package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCTO")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String descripcion;
    private Double precioUnitario;
    private Long existencias;
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;

}
