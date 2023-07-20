package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Integer talle;
    private Double precioUnitario;
    private Long existencias;
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    private Date fechaBaja;

}
