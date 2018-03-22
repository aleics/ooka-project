import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  constructor(
    private jwtHelperService: JwtHelperService
  ) {
  }

  public isTokenExpired(): boolean {
    const token = this.jwtHelperService.tokenGetter();
    return token ?
      this.jwtHelperService.isTokenExpired(token) :
      true;
  }
}
