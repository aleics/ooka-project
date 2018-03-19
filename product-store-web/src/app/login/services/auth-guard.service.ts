import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from './auth.service';

import * as _ from 'lodash';


@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  canActivate(): boolean {
    if (this.authService.isTokenExpired()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
