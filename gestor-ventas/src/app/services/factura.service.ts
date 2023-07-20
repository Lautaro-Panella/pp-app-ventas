import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { Factura } from '../models/factura';
import { FacturaDTO } from '../models/facturaDTO';
import { FiltroFechasDTO } from '../models/filtroFechasDTO';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  private facturaUrl: string = environment.facturaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las facturas
  getAllFacturas(): Observable<Factura[]> {
    return this.http.get<Factura[]>(this.facturaUrl, { headers: this.header });
  }

  // Obtener una factura por id
  getFacturaById(id: any): Observable<Factura> {
    return this.http.get<Factura>(this.facturaUrl + '/' + id, { headers: this.header });
  }

  // Obtener facturas entre fechaDesde y fechaHasta
  getFacturasByFechaDesdeFechaHasta(filtroFechasDTO: FiltroFechasDTO): Observable<Factura[]> {
    return this.http.post<Factura[]>(this.facturaUrl + '/customFilter', filtroFechasDTO, { headers: this.header });
  }

  // Guardar-actualizar factura
  saveFactura(facturaDTO: FacturaDTO): Observable<FacturaDTO> {
    return this.http.post<FacturaDTO>(this.facturaUrl, facturaDTO, { headers: this.header });
  }

  // Enviar via mail una factura
  sendMail(facturaId: any, mail: string): Observable<boolean> {
    return this.http.get<boolean>(this.facturaUrl + '/sendMail?facturaId=' + facturaId + '&mail=' + mail, { headers: this.header });
  }

  getFacturaPDF(facturaId: any): string {
    return this.facturaUrl + '/files/' + facturaId;
  }

}
