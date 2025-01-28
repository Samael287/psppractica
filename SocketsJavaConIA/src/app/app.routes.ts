import { Routes } from '@angular/router';
import { LogginComponent } from './loggin/loggin.component';
import { ChatComponent } from './componentes/chat/chat.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    {path: 'register', component: RegisterComponent},
    {path: 'loggin', component: LogginComponent},
    {path: 'chat', component: ChatComponent},
    {path: '**', redirectTo: 'loggin'}
];
