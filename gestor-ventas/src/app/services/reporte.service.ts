import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

//Environment
import { environment } from 'src/environments/environment';

//Model
import { FiltroFechasDTO } from '../models/filtroFechasDTO';
import { RankingProductosDTO } from '../models/rankingProductosDTO';
import { HistoricoVentasDTO } from '../models/historicoVentasDTO';

//XLS
import * as FileSaver from 'file-saver';  
import * as XLSX from 'xlsx';
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';  
const EXCEL_EXTENSION = '.xlsx';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {

  private reporteUrl: string = environment.reporteUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener ganancia entre fechaDesde y fechaHasta
  getGanancia(filtroFechasDTO: FiltroFechasDTO): Observable<number> {
    return this.http.post<number>(this.reporteUrl + '/ganancia', filtroFechasDTO, { headers: this.header });
  }

  // Obtener productos más vendidos
  getProductosMasVendidos(): Observable<RankingProductosDTO[]> {
    return this.http.get<RankingProductosDTO[]>(this.reporteUrl + '/masVendidos', { headers: this.header });
  }

  // Obtener productos menos vendidos
  getProductosMenosVendidos(): Observable<RankingProductosDTO[]> {
    return this.http.get<RankingProductosDTO[]>(this.reporteUrl + '/menosVendidos', { headers: this.header });
  }

  // Obtener histórico de ventas
  getHistoricoVentas(): Observable<HistoricoVentasDTO[]> {
    return this.http.get<HistoricoVentasDTO[]>(this.reporteUrl + '/historicoVentas', { headers: this.header });
  }

  // Exportar datos de un json a excel
  public exportAsExcelFile(json: any[], excelFileName: string): void {  
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);  
    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };  
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });  
    this.saveAsExcelFile(excelBuffer, excelFileName);  
  }  

// Descargar archivo excel
  private saveAsExcelFile(buffer: any, fileName: string): void {  
    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});  
    FileSaver.saveAs(data, fileName + EXCEL_EXTENSION);  
  }

}
