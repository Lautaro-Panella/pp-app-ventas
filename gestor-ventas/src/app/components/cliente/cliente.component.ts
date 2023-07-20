import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

//Model
import { Cliente } from 'src/app/models/cliente';

//Service
import { ClienteService } from 'src/app/services/cliente.service';
import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  titulo: string = 'Listado de clientes:';
  tituloModal: string;
  textoSwitch: string;
  
  clientes: Cliente[];
  clienteId: any = null;
  fechaActual: any = null;

  clienteForm = new FormGroup({
    clienteId: new FormControl(),
    nombre: new FormControl('', Validators.required),
    apellido: new FormControl('', Validators.required),
    dni: new FormControl('', Validators.required),
    domicilio: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private clienteService: ClienteService, private alertaService: AlertaService) { }

  ngOnInit(): void {
    this.getAllClientesActivos();
  }

  getAllClientesActivos() {
    this.clienteService.getAllClientesActivos().subscribe(data =>{
      this.clientes = data;
      this.textoSwitch = "VER TODOS";
    });
  }

  filterClientes(event: any) {
    if(event.target.checked) {
      this.clienteService.getAllClientes().subscribe(data =>{
        this.clientes = data;
        this.textoSwitch = "VER ACTIVOS";
      });
    } else {
      this.getAllClientesActivos();
    }
  }

  getClientesByNombreOrApellidoOrDni(texto: string) {
    this.clienteService.getClientesByNombreOrApellidoOrDni(texto).subscribe(data => {
      this.clientes = data;
    });
  }

  clearForm() {
    this.clienteForm.setValue({
      'clienteId': '',
      'nombre': '', 
      'apellido': '', 
      'dni': '', 
      'domicilio': ''
    });
  }

  setClienteForEdit(clienteId: any) {
    this.clienteId = clienteId;
    if(clienteId != null) {
      this.tituloModal = "EDITAR CLIENTE";
      this.clienteService.getClienteById(clienteId).subscribe(data =>{
        this.clienteForm.setValue({
          'clienteId': data.clienteId,
          'nombre': data.nombre, 
          'apellido': data.apellido, 
          'dni': (data.dni).toString(), 
          'domicilio': data.domicilio
        });
      });
    } else {
      this.clearForm();
      this.tituloModal = "NUEVO CLIENTE";
    }
  }

  postForm(form: any) {
    let cliente: Cliente = {"clienteId": form.clienteId, "nombre": form.nombre, "apellido": form.apellido,
    "dni": form.dni, "domicilio": form.domicilio, "fechaBaja": this.fechaActual}
    this.clienteService.saveCliente(cliente).subscribe(data =>{
      if(data == null) {
        this.alertaService.mostrarError("No se pudo guardar el Cliente!", "Error");
      } else {
        this.alertaService.mostrarSuccess("Cliente guardado!", "Hecho");
        this.getAllClientesActivos();
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
