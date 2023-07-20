import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

//Model
import { Producto } from 'src/app/models/producto';
import { Categoria } from 'src/app/models/categoria';

//Service
import { ProductoService } from 'src/app/services/producto.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {

  titulo: string = 'Listado de productos:';
  tituloModal: string;
  textoSwitch: string;
  
  productos: Producto[];
  categorias: Categoria[];
  productoId: any = null;
  fechaActual: any = null;

  productoForm = new FormGroup({
    productoId: new FormControl(),
    descripcion: new FormControl('', Validators.required),
    talle: new FormControl('', Validators.required),
    precioUnitario: new FormControl('', Validators.required),
    existencias: new FormControl('', Validators.required),
    categoria: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private productoService: ProductoService, private categoriaService: CategoriaService, private alertaService: AlertaService) { }

  ngOnInit(): void {
    this.categoriaService.getAllCategoriasActivas().subscribe(data =>{
      this.categorias = data;
    });
    this.getAllProductosActivos();
  }

  getAllProductosActivos() {
    this.productoService.getAllProductosActivos().subscribe(data =>{
      this.productos = data;
      this.textoSwitch = "VER TODOS";
    });
  }

  filterProductos(event: any) {
    if(event.target.checked) {
      this.productoService.getAllProductos().subscribe(data =>{
        this.productos = data;
        this.textoSwitch = "VER ACTIVOS";
      });
    } else {
      this.getAllProductosActivos();
    }
  }

  getProductosByDescripcion(texto: string) {
    this.productoService.getProductosByDescripcion(texto).subscribe(data => {
      this.productos = data;
    });
  }

  clearForm() {
    this.productoForm.setValue({
      'productoId': '',
      'descripcion': '',
      'talle': '', 
      'precioUnitario': '', 
      'existencias': '', 
      'categoria': ''
    });
  }

  setProductoForEdit(productoId: any) {
    this.productoId = productoId;
    if(productoId != null) {
      this.tituloModal = "EDITAR PRODUCTO";
      this.productoService.getProductoById(productoId).subscribe(data =>{
        this.productoForm.setValue({
          'productoId': data.productoId,
          'descripcion': data.descripcion,
          'talle': (data.talle).toString(),
          'precioUnitario': (data.precioUnitario).toString(), 
          'existencias': (data.existencias).toString(), 
          'categoria': (data.categoria.categoriaId).toString()
        });
      });
    } else {
      this.clearForm();
      this.tituloModal = "NUEVO PRODUCTO";
    }
  }

  postForm(form: any) {
    this.categoriaService.getCategoriaById(form.categoria).subscribe(categoria => {
      let producto: Producto = {"productoId": form.productoId, "descripcion": form.descripcion, "talle": form.talle, "precioUnitario": form.precioUnitario,
      "existencias": form.existencias, "categoria": categoria, "fechaBaja": this.fechaActual}
      this.productoService.saveProducto(producto).subscribe(data =>{
        if(data == null) {
          this.alertaService.mostrarError("No se pudo guardar el Producto!", "Error");
        } else {
          this.alertaService.mostrarSuccess("Producto guardado!", "Hecho");
          this.getAllProductosActivos();
        }
      });
    });  
  }

  setFechaBaja(event: any) {
    if(event.target.checked) {
      this.fechaActual = new Date();
    } else {
      this.fechaActual = null;
    }
  }

  getTalleDescripcion(talle: number): string {
    return this.productoService.getTalleDescripcion(talle);
  }

}
