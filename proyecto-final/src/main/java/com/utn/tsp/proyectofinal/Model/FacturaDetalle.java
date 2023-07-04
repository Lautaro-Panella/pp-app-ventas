package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FACTURA_DETALLE")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaDetalleId;
    @ManyToOne()
    @JoinColumn(name = "facturaId")
    private Factura factura;
    @ManyToOne()
    @JoinColumn(name = "productoId")
    private Producto producto;
    private Double precioUnitario;
    private int cantidad;

}
