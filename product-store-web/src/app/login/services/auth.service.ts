import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import 'rxjs/add/operator/map';
import { StorageService } from './storage.service';

@Injectable()
export class AuthService {

  private jwtToken: string;

  constructor(
    private jwtHelperService: JwtHelperService,
    storageService: StorageService
  ) {
    const tokenData = storageService.getTokenData();
    if (tokenData) {
      this.jwtToken = tokenData.token;
    }
  }

  public isTokenExpired(): boolean {
    return this.jwtToken ?
      this.jwtHelperService.isTokenExpired(this.jwtToken) :
      true;
  }
}
