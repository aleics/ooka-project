import { Injectable } from '@angular/core';

@Injectable()
export class EndpointService {

  private storeBaseUrl: string = 'http://172.10.0.5/api';

  constructor() {}

  public getStoreEndpoint(): string {
    return this.storeBaseUrl;
  }

  public getProductsEndpoint(): string {
    return `${this.storeBaseUrl}/graphql`;
  }
}