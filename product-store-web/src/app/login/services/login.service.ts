import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EndpointService } from '../../general/services/endpoint.service';
import { Credentials, TokenData, Authority } from '../models';
import { Observable } from 'rxjs/Observable';
import * as _ from 'lodash';

import 'rxjs/add/operator/map';
import { HttpResponse } from 'selenium-webdriver/http';

@Injectable()
export class LoginService {

  private baseUrl: string;
  private authHeader = 'Authorization';

  constructor(
    endpointService: EndpointService,
    private http: HttpClient
  ) {
    this.baseUrl = endpointService.getLoginEndpoint();
  }

  public login(credentials: Credentials): Observable<TokenData> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

      const body = JSON.stringify(credentials);

      return this.http.post(this.baseUrl, body, { headers, observe: 'response' })
        .map((res) => {
          const authorization = res.headers.get(this.authHeader);
          const authorities = res.body as Authority[];
          const accountType = authorities[0].authority;
          return {
            token: this.extractToken(authorization),
            accountType
          };
        });
  }

  private extractToken(authHeader: string) {
    const token = authHeader.replace('Bearer', '');
    return _.trim(token);
  }
}
