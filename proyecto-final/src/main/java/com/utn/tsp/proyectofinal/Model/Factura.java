package com.utn.tsp.proyectofinal.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FACTURA")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaId;
    private Date fecha;
    private Double porcentajeDescuento;
    private Double subtotal;
    private String observaciones;
    @ManyToOne()
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

}
