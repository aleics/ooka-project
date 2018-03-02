import { Injectable } from '@angular/core';
import { EndpointService } from './endpoint.service';
import { Product, GraphQLRequest, ProductFilter } from '../models';
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
    const query = `
      {
        allProducts {
          id
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

  public getProduct(filter: ProductFilter): Observable<Product> {
    const queryFilter = `filter: { id: "${filter.id}" }`;
    const query = `
      {
        allProducts(${queryFilter}) {
          id
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

        return <Product>response.data.allProducts[0];
      });
  }

  private executeQuery(query: string): Observable<any> {
    const graphQLRequest: GraphQLRequest = {
      query
    };

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const options: RequestOptionsArgs = {
      headers
    };

    const body = JSON.stringify(graphQLRequest);

    return this.http.post(this.baseUrl, body, options)
      .map((response) => response.json());
  }
}
