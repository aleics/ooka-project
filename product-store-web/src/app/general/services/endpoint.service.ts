import { Injectable } from '@angular/core';

@Injectable()
export class EndpointService {

  private storeBaseUrl = 'http://172.11.0.3:8080/api';

  constructor() {}

  public getStoreEndpoint(): string {
    return this.storeBaseUrl;
  }

  public getProductsEndpoint(): string {
    return `${this.storeBaseUrl}/graphql`;
  }
}