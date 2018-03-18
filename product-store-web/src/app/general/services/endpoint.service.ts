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

  public getUserMngmtEndpoint(): string {
    return `${this.storeBaseUrl}/usermngmt/api`;
  }

  public getLoginEndpoint(): string {
    return `${this.getUserMngmtEndpoint()}/v1/login`;
  }

  public getAuthEndpoint(): string {
    return `${this.getUserMngmtEndpoint()}/v1/auth`;
  }
}
