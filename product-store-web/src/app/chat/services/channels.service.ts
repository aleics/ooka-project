import { Injectable, EventEmitter } from '@angular/core';
import { ChatEvent, ChatEventType, ChatMessage, ChatChannel } from '../models';
import { EndpointService } from '../../general/services';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import * as _ from 'lodash';

@Injectable()
export class ChannelsService {
  private baseUrl: string;
  constructor(
    endpointService: EndpointService,
    private http: HttpClient
  ) {
    this.baseUrl = endpointService.getChannelsEndpoint();
  }

  loadChannels(): Observable<ChatChannel[]> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    return this.http.get<ChatChannel[]>(this.baseUrl, { headers });
  }

  createChannel(channel: ChatChannel): Observable<ChatChannel> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    const body = JSON.stringify(channel);

    return this.http.post<ChatChannel>(this.baseUrl, body, { headers });
  }
}
