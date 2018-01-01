import { Injectable } from '@angular/core';
import { EndpointService } from './endpoint.service';
import { Product, GraphQLRequest } from '../models';
import { Http, RequestOptionsArgs, Headers } from '@angular/http';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import 'rxjs/add/operator/map';

@Injectable()
export class ProductsService {

  private baseUrl: string;

  constructor(
    endpointService: EndpointService,
    private http: Http
  ) {
    this.baseUrl = endpointService.getProductsEndpoint();
  }

  public getProducts(): Observable<Product[]> {
    const query: string = `
      {
        allProducts {
          name
          description
          price
          available
        }
      }
    `;

    return this.executeQuery(query)
      .map((response) => {
        if (response.error) {
          console.error(response.error);
          return null;
        }

        return <Product[]>response.data.allProducts;
      });
  }

  private executeQuery(query: string): Observable<any> {
    const graphQLRequest: GraphQLRequest = {
      query
    };

    let headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const options: RequestOptionsArgs = {
      headers
    };

    const body = JSON.stringify(graphQLRequest);

    return this.http.post(this.baseUrl, body, options)
      .map((response) => response.json());
  }
}