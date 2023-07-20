import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

//Model
import { Categoria } from 'src/app/models/categoria';

//Service
import { CategoriaService } from 'src/app/services/categoria.service';
import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  titulo: string = 'Listado de categorias:';
  tituloModal: string;
  textoSwitch: string;
  
  categorias: Categoria[];
  categoriaId: any = null;
  fechaActual: any = null;

  categoriaForm = new FormGroup({
    categoriaId: new FormControl(),
    descripcion: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private categoriaService: CategoriaService, private alertaService: AlertaService) { }

  ngOnInit(): void {
    this.getAllCategoriasActivas();
  }

  getAllCategoriasActivas() {
    this.categoriaService.getAllCategoriasActivas().subscribe(data =>{
      this.categorias = data;
      this.textoSwitch = "VER TODAS";
    });
  }

  filterCategorias(event: any) {
    if(event.target.checked) {
      this.categoriaService.getAllCategorias().subscribe(data =>{
        this.categorias = data;
        this.textoSwitch = "VER ACTIVAS";
      });
    } else {
      this.getAllCategoriasActivas();
    }
  }

  getCategoriasByDescripcion(texto: string) {
    this.categoriaService.getCategoriasByDescripcion(texto).subscribe(data => {
      this.categorias = data;
    });
  }

  clearForm() {
    this.categoriaForm.setValue({
      'categoriaId': '',
      'descripcion': ''
    });
  }

  setCategoriaForEdit(categoriaId: any) {
    this.categoriaId = categoriaId;
    if(categoriaId != null) {
      this.tituloModal = "EDITAR CATEGORIA";
      this.categoriaService.getCategoriaById(categoriaId).subscribe(data =>{
        this.categoriaForm.setValue({
          'categoriaId': data.categoriaId,
          'descripcion': data.descripcion
        });
      });
    } else {
      this.clearForm();
      this.tituloModal = "NUEVA CATEGORIA";
    }
  }

  postForm(form: any) {
    let categoria: Categoria = {"categoriaId": form.categoriaId, "descripcion": form.descripcion, "fechaBaja": this.fechaActual}
    this.categoriaService.saveCategoria(categoria).subscribe(data =>{
      if(data == null) {
        this.alertaService.mostrarError("No se pudo guardar la Categoría!", "Error");
      } else {
        this.alertaService.mostrarSuccess("Categoría guardada!", "Hecho");
        this.getAllCategoriasActivas();
      }
    });
  }

  setFechaBaja(event: any) {
    if(event.target.checked) {
      this.fechaActual = new Date();
    } else {
      this.fechaActual = null;
    }
  }

}
