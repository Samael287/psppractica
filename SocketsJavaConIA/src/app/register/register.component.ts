import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserchatServiceService } from '../userchat-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username = '';
  email = '';
  password = '';
  
  constructor(private router: Router, private userchatService: UserchatServiceService){}

  register(){
    const usuario = { username: this.username, email: this.email, password: this.password };
     // Verifica si el usuario tiene valores antes de hacer la solicitud
     if (this.username && this.email && this.password) {
      this.userchatService.register(usuario).subscribe(
        (response) => {
          Swal.fire({
            icon: 'success',
            title: 'Registro exitoso',
            text: 'Bienvenido',
          }).then(() => {
            this.router.navigate(['/loggin']);
          });
        },
        (error) => {
          Swal.fire({
            icon: 'error',
            title: 'Registro fallido',
            text: `Error: ${error.message}`,
          });
        }
      );
    }else{
      Swal.fire({
        icon: 'error',
        title: 'Registro fallido',
        text: `${this.username} ;;; ${this.email} ;;; ${this.password}`,  
      });
    }
  }
}
