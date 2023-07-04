import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private clienteUrl: string = environment.clienteUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los clientes
  getAllClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.clienteUrl, { headers: this.header });
  }

  // Obtener todos los clientes activos
  getAllClientesActivos(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.clienteUrl + "/activos", { headers: this.header });
  }

  // Obtener un cliente por id
  getClienteById(id: any): Observable<Cliente> {
    return this.http.get<Cliente>(this.clienteUrl + '/' + id, { headers: this.header });
  }

  // Guardar-actualizar cliente
  saveCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.clienteUrl, cliente, { headers: this.header });
  }

}
