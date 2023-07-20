import { Factura } from "./factura";
import { FacturaDetalle } from "./facturaDetalle";

export interface FacturaDTO {

    factura: Factura;
    facturaDetalles: FacturaDetalle[];
}