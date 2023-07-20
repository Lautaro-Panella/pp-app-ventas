import { Factura } from "./factura";
import { Producto } from "./producto";

export interface FacturaDetalle{

    facturaDetalleId: number | any;
    factura: Factura | any;
    producto: Producto;
    precioUnitario: number;
    cantidad: number;

}