import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

import * as _ from 'lodash';
import { AuthService } from '../../login/services/auth.service';


@Injectable()
export class ChatGuardService implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  canActivate(): boolean {
    const permission = this.authService.userPermission();
    if (permission.accountType !== 'PREMIUM' && permission.userRole === 'USER') {
      this.router.navigate(['/home']);
      return false;
    }

    return true;
  }
}
