package com.utn.tsp.proyectofinal.Model.DTO;

import com.utn.tsp.proyectofinal.Model.Factura;
import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {

    private Factura factura;
    private List<FacturaDetalle> facturaDetalles;

}
