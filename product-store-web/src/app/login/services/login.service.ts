import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EndpointService } from '../../general/services/endpoint.service';
import { Credentials } from '../models';
import { Observable } from 'rxjs/Observable';
import { HttpResponse } from 'selenium-webdriver/http';
import * as _ from 'lodash';

import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {

  private baseUrl: string;
  private authHeader = 'Authorization';
  private authScheme = 'Bearer ';

  constructor(
    endpointService: EndpointService,
    private http: HttpClient
  ) {
    this.baseUrl = endpointService.getLoginEndpoint();
  }

  public login(credentials: Credentials): Observable<string> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

      const body = JSON.stringify(credentials);

      return this.http.post(this.baseUrl, body, { headers, observe: 'response' })
        .map((res) => {
          const authorization = res.headers.get(this.authHeader);
          return this.extractToken(authorization);
        });
  }

  private extractToken(authHeader: string) {
    const token = authHeader.replace(this.authScheme, '');
    return _.trim(token);
  }
}
