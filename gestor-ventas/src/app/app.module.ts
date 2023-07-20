import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';

// Rutas
import { AppRoutingModule } from './app-routing.module';

// Peticiones HTTP
import { HttpClientModule } from '@angular/common/http';

// Formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Alertas
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AlertaService } from './services/alerta.service';

// Header y Footer
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';

// Inicio
import { InicioComponent } from './components/inicio/inicio/inicio.component';

// Clientes
import { ClienteComponent } from './components/cliente/cliente.component';
import { ClienteService } from 'src/app/services/cliente.service';

// Categorías
import { CategoriaComponent } from './components/categoria/categoria.component';
import { CategoriaService } from 'src/app/services/categoria.service';

// Productos
import { ProductoComponent } from './components/producto/producto.component';
import { ProductoService } from 'src/app/services/producto.service';

// Ventas-Facturas
import { FacturaComponent } from './components/factura/factura.component';
import { FacturaService } from 'src/app/services/factura.service';
import { FacturaDetalleService } from 'src/app/services/factura-detalle.service';

// Reportes-Métricas
import { ReporteComponent } from './components/reporte/reporte.component';
import { ReporteService } from 'src/app/services/reporte.service';
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    InicioComponent,
    ClienteComponent,
    CategoriaComponent,
    ProductoComponent,
    FacturaComponent,
    ReporteComponent 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [AlertaService, ClienteService, CategoriaService, ProductoService, FacturaService, FacturaDetalleService, ReporteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
