import { Injectable } from '@angular/core';

@Injectable()
export class EndpointService {

  private storeBaseUrl = 'http://local.product-store.com/api'; // local debugging: http://localhost:8080/api

  constructor() {}

  public getStoreEndpoint(): string {
    return this.storeBaseUrl;
  }

  public getProductsEndpoint(): string {
    return `${this.storeBaseUrl}/graphql`;
  }
}