import { Injectable } from '@angular/core';
import { TokenData } from '../models';


export function tokenGetter() {
  const storageData = localStorage.getItem('ps-login-info');
  const data: TokenData = JSON.parse(storageData);
  return data.token;
}

@Injectable()
export class StorageService {
  private key = 'ps-login-info';

  constructor() {}

  public saveTokenData(tokenData: TokenData) {
    const data = JSON.stringify(tokenData);
    this.set(this.key, data);
  }

  public getTokenData(): TokenData {
    const data = this.get(this.key);
    return JSON.parse(data);
  }

  private set(key: string, data: string) {
    localStorage.setItem(key, data);
  }

  private get(key: string) {
    return localStorage.getItem(key);
  }
}
