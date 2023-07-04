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

// Header
import { HeaderComponent } from './components/header/header.component';

// Inicio
import { InicioComponent } from './components/inicio/inicio/inicio.component';

// Clientes
import { ClienteComponent } from './components/cliente/cliente.component';
import { ClienteService } from 'src/app/services/cliente.service';

// Categorías
import { CategoriaComponent } from './components/categoria/categoria.component';

// Productos
import { ProductoComponent } from './components/producto/producto.component';

// Ventas-Facturas
import { FacturaComponent } from './components/factura/factura.component';

// Reportes-Métricas
import { ReporteComponent } from './components/reporte/reporte.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
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
  providers: [AlertaService, ClienteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
