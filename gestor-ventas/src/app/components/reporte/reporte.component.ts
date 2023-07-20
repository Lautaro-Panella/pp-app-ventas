import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

// Model
import { FiltroFechasDTO } from 'src/app/models/filtroFechasDTO';
import { RankingProductosDTO } from 'src/app/models/rankingProductosDTO';
import { HistoricoVentasDTO } from 'src/app/models/historicoVentasDTO';

// Service
import { ReporteService } from 'src/app/services/reporte.service';
import { ProductoService } from 'src/app/services/producto.service';
import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-reporte',
  templateUrl: './reporte.component.html',
  styleUrls: ['./reporte.component.css']
})
export class ReporteComponent implements OnInit {

  titulo: string = 'Reportes y Métricas:';
  textoSwitch: string;
  ganancia: number;
  rankingProductosDTO: RankingProductosDTO[] = [];
  productoEstrella: RankingProductosDTO;
  historicoVentasDTO: HistoricoVentasDTO[] = [];

  fechasForm = new FormGroup({
    fechaDesde: new FormControl('', Validators.required),
    fechaHasta: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private reporteService: ReporteService, private productoService: ProductoService, private alertaService: AlertaService) { }

  ngOnInit(): void {
    let fechas: FiltroFechasDTO = {"fechaDesde": new Date('2000-1-1'), "fechaHasta": new Date()};
    this.reporteService.getGanancia(fechas).subscribe(data =>{
      this.ganancia = data;
    });
    this.getProductosMasVendidos();
    this.getHistoricoVentas();
  }

  postFormFechas(form: any) {
    let fechas: FiltroFechasDTO = {"fechaDesde": form.fechaDesde, "fechaHasta": form.fechaHasta};
    this.reporteService.getGanancia(fechas).subscribe(data =>{
      this.ganancia =  data;
    });
  }

  getProductosMasVendidos() {
    this.reporteService.getProductosMasVendidos().subscribe(data =>{
      this.rankingProductosDTO = data;
      this.productoEstrella = data[0];
      this.textoSwitch = "VER MENOS VENDIDOS";
    });
  }

  getProductosMenosVendidos() {
    this.reporteService.getProductosMenosVendidos().subscribe(data =>{
      this.rankingProductosDTO = data;
      this.textoSwitch = "VER MAS VENDIDOS";
    });
  }

  filterRanking(event: any) {
    if(event.target.checked) {
      this.getProductosMenosVendidos();
    } else {
      this.getProductosMasVendidos();
    }
  }

  getHistoricoVentas() {
    this.reporteService.getHistoricoVentas().subscribe(data =>{
      this.historicoVentasDTO = data;
    });
  }

  getTalleDescripcion(talle: number): string {
    return this.productoService.getTalleDescripcion(talle);
  }

  getRankingProductosXLS() {
    this.reporteService.exportAsExcelFile(this.rankingProductosDTO, 'Ranking_productos');
  }

  getHistoricoVentasXLS() {
    this.reporteService.exportAsExcelFile(this.historicoVentasDTO, 'Historico_ventas');
  }

}
