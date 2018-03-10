import { Injectable } from '@angular/core';
import { EndpointService } from './endpoint.service';
import { Product, GraphQLRequest, ProductFilter } from '../models';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';

@Injectable()
export class ProductsService {

  private baseUrl: string;

  constructor(
    endpointService: EndpointService,
    private http: HttpClient
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
        product(${queryFilter}) {
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

        return <Product>response.data.product;
      });
  }

  private executeQuery(query: string): Observable<any> {
    const graphQLRequest: GraphQLRequest = {
      query
    };

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    const body = JSON.stringify(graphQLRequest);

    return this.http.post(this.baseUrl, body, { headers });
  }
}
