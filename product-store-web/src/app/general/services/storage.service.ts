import { Injectable } from '@angular/core';
import { User } from '../models';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TokenData } from '../../login/models/token-data.interface';


export function tokenGetter() {
  return localStorage.getItem(tokenKey);
}

export const tokenKey = 'ps-token';

@Injectable()
export class StorageService {

  constructor(
    private jwtHelperService: JwtHelperService
  ) {}

  public saveToken(token: string) {
    this.set(tokenKey, token);
  }

  public getUserData(): User {
    const token = this.get(tokenKey);

    const tokenData = this.jwtHelperService.decodeToken(token) as TokenData;
    return JSON.parse(tokenData.user) as User;
  }

  public removeToken() {
    localStorage.removeItem(tokenKey);
  }

  private set(key: string, data: string) {
    localStorage.setItem(key, data);
  }

  private get(key: string) {
    return localStorage.getItem(key);
  }
}
