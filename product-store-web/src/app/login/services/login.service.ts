import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EndpointService } from '../../general/services/endpoint.service';
import { Credentials, TokenData } from '../models';
import { Observable } from 'rxjs/Observable';
import * as _ from 'lodash';

import 'rxjs/add/operator/map';

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

  public login(credentials: Credentials): Observable<void> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

      const body = JSON.stringify(credentials);

      return this.http.post(this.baseUrl, body, { headers })
        .map((res: Response) => {
          console.log(res);

          // let header = res.headers.get(this.authHeader);
          // const token = _.trim(header.replace('Bearer', ''));
          // const accountType = res
        });
  }
}
