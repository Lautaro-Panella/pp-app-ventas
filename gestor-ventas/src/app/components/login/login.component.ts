import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { AlertaService } from 'src/app/services/alerta.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit {

  titulo: string = 'Bienvenid@!';
  user: string = 'romi';
  password: string = '1234'

  loginForm = new FormGroup({
    user: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private alertaService: AlertaService) { }

  ngOnInit(): void {
    
  }

  postForm(form: any) {
    if(form.user == this.user && form.password == this.password) {
      this.alertaService.mostrarSuccess("Bienvenid@!", "Hecho");
      localStorage.setItem('isLogged', 'true');
      this.router.navigate(['dashboard']);
    } else {
      this.alertaService.mostrarError("Usuario o Contraseña incorrecta!", "Error");
    }
  }

}
