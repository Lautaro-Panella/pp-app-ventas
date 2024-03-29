import { NgModule } from '@angular/core';

// Rutas
import { RouterModule, Routes } from '@angular/router';

// Login
import { LoginComponent } from './components/login/login.component';

// Header
import { HeaderComponent } from './components/header/header.component';

// Inicio
import { InicioComponent } from './components/inicio/inicio/inicio.component';

// Clientes
import { ClienteComponent } from './components/cliente/cliente.component';

// Categorías
import { CategoriaComponent } from './components/categoria/categoria.component';

// Productos
import { ProductoComponent } from './components/producto/producto.component';

// Ventas-Facturas
import { FacturaComponent } from './components/factura/factura.component';

// Reportes-Métricas
import { ReporteComponent } from './components/reporte/reporte.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'', component: HeaderComponent},
  {path:'dashboard', component: InicioComponent},
  {path:'clientes', component: ClienteComponent},
  {path:'categorias', component: CategoriaComponent},
  {path:'productos', component: ProductoComponent},
  {path:'facturas', component: FacturaComponent},
  {path:'reportes', component: ReporteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
