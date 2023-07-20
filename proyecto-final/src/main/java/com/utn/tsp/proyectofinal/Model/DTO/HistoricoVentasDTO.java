package com.utn.tsp.proyectofinal.Model.DTO;

import java.util.Date;

public interface HistoricoVentasDTO {

    Long getFacturaId();
    Date getFecha();
    String getObservaciones();
    Double getSubtotal();
    Double getPorcentajeDescuento();
    String getCliente();
    Long getFacturaDetalleId();
    String getProducto();
    Integer getTalle();
    Integer getCantidad();
    Double getPrecioUnitario();

}
