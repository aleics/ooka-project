import { Injectable } from '@angular/core';
import { EndpointService } from './endpoint.service';
import { Product, GraphQLRequest, ProductFilter, AllProductsFilter } from '../models';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as _ from 'lodash';

import 'rxjs/add/operator/map';

@Injectable()
export class ProductsService {

  private baseUrl: string;

  constructor(
    endpointService: EndpointService,
    private http: HttpClient
  ) {
    this.baseUrl = endpointService.getProductsGraphQLEndpoint();
  }

  public getProducts(filter: AllProductsFilter = null): Observable<Product[]> {
    const queryFilter = filter ? `(filter: { q: "${filter.q}" })` : '';
    const query = `
      {
        allProducts${queryFilter} {
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

        return <Product[]>_.get(response, 'data.allProducts');
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

        return <Product>_.get(response, 'data.product');
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
