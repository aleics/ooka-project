import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuardService } from './services/auth-guard.service';
import { AuthService } from './services/auth.service';
import { tokenGetter } from '../general/services/storage.service';
import { LoginService } from './services/login.service';
import { JwtModule } from '@auth0/angular-jwt';

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,

    JwtModule.forRoot({
      config: {
        tokenGetter,
        whitelistedDomains: ['local.store.com', 'localhost'],
        blacklistedRoutes: ['http://local.store.com/usermngmt/api/v1/login', 'http://localhost:8080/api/v1/login']
      }
    })
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    AuthGuardService,
    AuthService,
    LoginService
  ]
})
export class LoginModule { }
