import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

//Model
import { Factura } from 'src/app/models/factura';
import { FacturaDTO } from 'src/app/models/facturaDTO';
import { FacturaDetalle } from 'src/app/models/facturaDetalle';
import { Cliente } from 'src/app/models/cliente';
import { Producto } from 'src/app/models/producto';
import { FiltroFechasDTO } from 'src/app/models/filtroFechasDTO';

//Service
import { FacturaService } from 'src/app/services/factura.service';
import { FacturaDetalleService } from 'src/app/services/factura-detalle.service';
import { ClienteService } from 'src/app/services/cliente.service';
import { ProductoService } from 'src/app/services/producto.service';
import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  // Título del Módulo
  titulo: string = 'Listado de facturas:';

  // Variables
  // Facturas a mostrar en Grilla
  facturas: Factura[] = [];
  // Factura_Id a mostrar en Detalle
  facturaId: number;
  // Clientes a mostrar en Formulario de Nueva Factura
  clientes: Cliente[] = [];
  // Productos a mostrar en Formulario de Nueva Factura
  productosForView: Producto[] = [];
  // Productos a guardar
  productosForSave: Producto[] = [];
  // FacturaDetalles a mostrar en Detalle
  facturaDetallesForView: FacturaDetalle[] = [];
  // FacturaDetalles a guardar
  facturaDetallesForSave: FacturaDetalle[] = [];
  // Cliente seleccionado en Formulario de Nueva Factura
  clienteSeleccionado: Cliente;
  // Producto seleccionado en Formulario de Nueva Factura
  productoSeleccionado: Producto;
  // Cantidad ingresada en Formulario de Nueva Factura
  cantidadIngresada: number;

  // Formulario de Búsqueda entre Fechas
  fechasForm = new FormGroup({
    fechaDesde: new FormControl('', Validators.required),
    fechaHasta: new FormControl('', Validators.required)
  });

  // Formulario de Nueva Factura
  facturaForm = new FormGroup({
    fecha: new FormControl('', Validators.required),
    porcentajeDescuento: new FormControl('', Validators.required),
    observaciones: new FormControl('', Validators.required),
    cliente: new FormControl('', Validators.required),
    producto: new FormControl('', Validators.required)
  });

  // Formulario de Envío de Factura vía Mail
  mailForm = new FormGroup({
    mail: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private facturaService: FacturaService, private facturaDetalleService: FacturaDetalleService, 
    private clienteService: ClienteService, private productoService: ProductoService, private alertaService: AlertaService,
    private changeRef: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getAllFacturas();
    this.getAllClientesActivos();
    this.getAllProductosActivos();
  }

  getAllFacturas() {
    this.facturaService.getAllFacturas().subscribe(data =>{
      this.facturas = data;
    });
  }

  getTalleDescripcion(talle: number): string {
    return this.productoService.getTalleDescripcion(talle);
  }

  getAllClientesActivos() {
    this.clienteService.getAllClientesActivos().subscribe(data =>{
      this.clientes = data;
    });
  }

  getAllProductosActivos() {
    this.productoService.getAllProductosActivos().subscribe(data =>{
      this.productosForView = data;
    });
  }

  setFacturaForView(facturaId: number) {
    this.facturaId = facturaId;
    this.facturaDetalleService.getFacturaDetallesByFacturaId(facturaId).subscribe(data =>{
      this.facturaDetallesForView = data;
    });
  }

  setClienteSeleccionado(clienteId: any) {
    this.clienteService.getClienteById(clienteId).subscribe(data =>{ this.clienteSeleccionado = data });
  }

  setProductoSeleccionado(productoId: any) {
    this.productoService.getProductoById(productoId).subscribe(data =>{
      this.productoSeleccionado = data;
    });
  }

  setCantidadIngresada(cantidad: any) {
    this.cantidadIngresada = cantidad;
  }

  getSubtotalFactura(): number {
    let subtotal = 0;
    this.facturaDetallesForSave.forEach((facturaDetalle) =>{
      subtotal += (facturaDetalle.precioUnitario * facturaDetalle.cantidad);
    });
    return subtotal;
  }

  addProducto() {
    if((this.cantidadIngresada <= 0) || (this.cantidadIngresada > this.productoSeleccionado.existencias)) {
      this.alertaService.mostrarError("Cantidad no permitida!", "Error");
    } else {
      let facturaDetalle: FacturaDetalle = {"facturaDetalleId": null, "factura": null, "producto": this.productoSeleccionado,
        "precioUnitario": this.productoSeleccionado.precioUnitario, "cantidad": this.cantidadIngresada };
      this.facturaDetallesForSave.push(facturaDetalle);
      this.productosForSave.push(facturaDetalle.producto);
      this.productosForSave.forEach((data) =>{
        this.productosForView = this.productosForView.filter(producto => producto.productoId != data.productoId);
      });
    }
  }

  postFormFechas(form: any) {
    let fechas: FiltroFechasDTO = {"fechaDesde": form.fechaDesde, "fechaHasta": form.fechaHasta};
    this.facturaService.getFacturasByFechaDesdeFechaHasta(fechas).subscribe(data =>{
      this.facturas = data;
    });
  }

  postForm(form: any) {
    // Se crea la Factura a Guardar
    let factura: Factura = {"facturaId": null, "fecha": form.fecha, "porcentajeDescuento": form.porcentajeDescuento,
      "subtotal": this.getSubtotalFactura(), "observaciones": form.observaciones, "cliente": this.clienteSeleccionado};
    // Se guarda la Factura
    let facturaDTO: FacturaDTO = {"factura": factura, "facturaDetalles": this.facturaDetallesForSave};
    this.facturaService.saveFactura(facturaDTO).subscribe(facturaDTO =>{
      if(facturaDTO == null) {
        this.alertaService.mostrarError("No se pudo guardar la Factura!", "Error");
      } else {
        this.alertaService.mostrarSuccess("Factura guardada!", "Hecho");
        this.clearForm();
        this.getAllFacturas();
      }
    });
  }

  clearForm(){
    this.facturaForm.setValue({
      'fecha': '',
      'porcentajeDescuento': '',
      'observaciones': '',
      'cliente': '',
      'producto': ''
    });
    this.getAllProductosActivos();
    this.getAllProductosActivos();
    this.productosForSave = [];
    this.facturaDetallesForSave = [];
  }

  getFacturaPDF(facturaId: any) {
    window.location.href = this.facturaService.getFacturaPDF(facturaId);
  }

  sendMail(form: any) {
    if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form.mail))) {
      this.alertaService.mostrarError("Mail con formato inválido!", "Error");
    } else {
      this.facturaService.sendMail(this.facturaId, form.mail).subscribe(data =>{
        if(data == true) {
          this.alertaService.mostrarSuccess("Factura enviada por Mail!", "Hecho");
        } else {
          this.alertaService.mostrarError("No se pudo enviar la Factura por Mail!", "Error");
        }
      });
    }
    this.mailForm.setValue({ 'mail': '' });
  }

}
