import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Permission } from '../../general/models';
import { StorageService } from '../../general/services';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  constructor(
    private jwtHelperService: JwtHelperService,
    private storageService: StorageService
  ) {
  }

  public isTokenExpired(): boolean {
    const token = this.storageService.getToken();
    return token ?
      this.jwtHelperService.isTokenExpired(token) :
      true;
  }

  public userPermission(): Permission {
    const user = this.storageService.getUserData();
    return {
      accountType: user.accountType,
      userRole: user.userRole
    };
  }

  public chatAvailable(): boolean {
    const user = this.storageService.getUserData();
    return user ? user.accountType === 'PREMIUM' : false;
  }
}
