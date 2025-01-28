import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router'; 
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { UserchatServiceService } from '../userchat-service.service';

@Component({
  selector: 'app-loggin',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './loggin.component.html',
  styleUrl: './loggin.component.css'
})
export class LogginComponent {
  email = '';
  password = '';

  constructor(private router: Router, private userchatService: UserchatServiceService){}

  loggin(){

    const user = { email: this.email, password: this.password };

  this.userchatService.login(this.email, this.password).subscribe(
    (response) => {
      // Login exitoso
      Swal.fire({
        icon: 'success',
        title: 'Acceso permitido',
        text: 'Bienvenido',
      }).then(() => {
        this.router.navigate(['/chat']);
      });
    },
    (error) => {
      // Manejar el error en caso de credenciales incorrectas
      Swal.fire({
        icon: 'error',
        title: 'Acceso denegado',
        text: 'Correo o contraseña incorrectos.',
      });
    }
  );
}
}
