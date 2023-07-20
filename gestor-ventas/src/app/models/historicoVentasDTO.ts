export interface HistoricoVentasDTO {

    facturaId: number;
    fecha: Date;
    observaciones: string;
    subtotal: number;
    porcentajeDescuento: number;
    cliente: string;
    facturaDetalleId: number;
    producto: string;
    talle: number;
    cantidad: number;
    precioUnitario: number;
}