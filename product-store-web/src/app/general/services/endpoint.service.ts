import { Injectable } from '@angular/core';

@Injectable()
export class EndpointService {

  private storeBaseUrl = 'http://local.store.com'; // local debugging: http://localhost:8080/api

  constructor() {}

  public getStoreEndpoint(): string {
    return this.storeBaseUrl;
  }

  public getProductsEndpoint(): string {
    return `${this.storeBaseUrl}/products/api`;
  }

  public getProductsGraphQLEndpoint(): string {
    return `${this.getProductsEndpoint()}/v1/graphql`;
  }
}
