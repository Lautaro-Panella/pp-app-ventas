import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { FacturaDetalle } from '../models/facturaDetalle';

@Injectable({
  providedIn: 'root'
})
export class FacturaDetalleService {

  private facturaDetalleUrl: string = environment.facturaDetalleUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las factura detalles
  getAllFacturaDetalles(): Observable<FacturaDetalle[]> {
    return this.http.get<FacturaDetalle[]>(this.facturaDetalleUrl, { headers: this.header });
  }

  // Obtener una factura detalle por id
  getFacturaDetalleById(id: any): Observable<FacturaDetalle> {
    return this.http.get<FacturaDetalle>(this.facturaDetalleUrl + '/' + id, { headers: this.header });
  }

  // Obtener factura detalles por facturaId
  getFacturaDetallesByFacturaId(facturaId: any): Observable<FacturaDetalle[]> {
    return this.http.get<FacturaDetalle[]>(this.facturaDetalleUrl + '/customFilter?facturaId=' + facturaId, { headers: this.header });
  }

  // Guardar-actualizar factura detalle
  saveFacturaDetalle(facturaDetalle: FacturaDetalle): Observable<FacturaDetalle> {
    return this.http.post<FacturaDetalle>(this.facturaDetalleUrl, facturaDetalle, { headers: this.header });
  }

}
