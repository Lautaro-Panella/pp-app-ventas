import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private productoUrl: string = environment.productoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los productos
  getAllProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.productoUrl, { headers: this.header });
  }

  // Obtener todos los productos activos
  getAllProductosActivos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.productoUrl + "/activos", { headers: this.header });
  }

  // Obtener un producto por id
  getProductoById(id: any): Observable<Producto> {
    return this.http.get<Producto>(this.productoUrl + '/' + id, { headers: this.header });
  }

  // Obtener productos por descripción
  getProductosByDescripcion(texto: string): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.productoUrl + '/customFilter?texto=' + texto, { headers: this.header });
  }

  // Guardar-actualizar producto
  saveProducto(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.productoUrl, producto, { headers: this.header });
  }

  // Obtener la descripción de un talle por id
  getTalleDescripcion(talle: number): string {
    if(talle == 1) {
      return "XS";
    }
    if(talle == 2) {
      return "S";
    }
    if(talle == 3) {
      return "M";
    }
    if(talle == 4) {
      return "L";
    }
    if(talle == 5) {
      return "XL";
    }
    if(talle == 6) {
      return "Único";
    }
    return "";
  }

}
