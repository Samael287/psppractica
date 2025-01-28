import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserchatServiceService {

  private currentUserSubject = new BehaviorSubject<any>(null);
  private API_URL = 'http://localhost:8080/api/usuarios';
  
  constructor(private http: HttpClient) { }

    // Registrar un nuevo usuario
    register(usuario: any): Observable<any> {
      return this.http.post(`${this.API_URL}/registrar`, usuario);
    }
  
    // Iniciar sesión del usuario
    login(email: string, password: string): Observable<any> {
      return this.http.post(`${this.API_URL}/login`, { email, password }).pipe(
        tap((response: any) => {
          this.setCurrentUser(response);  // Guarda el usuario autenticado en el BehaviorSubject
        })
      );
    }
  
    // Obtener el usuario autenticado
    getUsuarioAutenticado(): Observable<any> {
      return this.http.get<any>(`${this.API_URL}/autenticado`);
    }
  
    // Verificar si el usuario está autenticado
    isAuthenticated(): Observable<boolean> {
      return this.http.get<boolean>(`${this.API_URL}/autenticado`);
    }
    setCurrentUser(user: any) {
      this.currentUserSubject.next(user);
    }
  
    // Obtener el usuario autenticado
    getCurrentUser(): Observable<any> {
      return this.currentUserSubject.asObservable();
    }
}
