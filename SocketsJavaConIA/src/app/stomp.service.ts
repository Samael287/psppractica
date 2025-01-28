import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as Stomp from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class StompService {

  private stompClient: Stomp.Client;

  constructor() {
    this.stompClient = new Stomp.Client({
      brokerURL: 'ws://localhost:8080/chat', // URL WebSocket
      connectHeaders: {
        // Aquí podrías pasar información del usuario si es necesario
      },
      debug: (str) => {
        console.log(str);
      }
    });
  }

  // Conectar al WebSocket
  connect(): void {
    this.stompClient.activate();
  }

  // Enviar un mensaje privado
  sendPrivateMessage(message: string, toUser: string): Observable<any> {
    return new Observable((observer) => {
      this.stompClient.publish({ destination: '/app/send-private-message', body: JSON.stringify({ message, toUser }) });
      observer.next('Mensaje privado enviado');
      observer.complete();
    });
  }

  // Suscribirse al canal privado de un usuario
  subscribeToPrivateMessages(username: string): void {
    if (this.stompClient.connected) {
      this.stompClient.subscribe(`/user/${username}/queue/messages`, (message) => {
        console.log('Mensaje privado recibido:', message.body);
      });
    } else {
      console.error('STOMP client is not connected');
    }
  }
}
