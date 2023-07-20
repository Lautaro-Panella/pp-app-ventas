import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private categoriaUrl: string = environment.categoriaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las categorías
  getAllCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.categoriaUrl, { headers: this.header });
  }

  // Obtener todas las categorías activas
  getAllCategoriasActivas(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.categoriaUrl + "/activos", { headers: this.header });
  }

  // Obtener una categoría por id
  getCategoriaById(id: any): Observable<Categoria> {
    return this.http.get<Categoria>(this.categoriaUrl + '/' + id, { headers: this.header });
  }

  // Obtener categorías por descripción
  getCategoriasByDescripcion(texto: string): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.categoriaUrl + '/customFilter?texto=' + texto, { headers: this.header });
  }

  // Guardar-actualizar categoría
  saveCategoria(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.categoriaUrl, categoria, { headers: this.header });
  }
  
}
