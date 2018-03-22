import { Injectable, EventEmitter } from '@angular/core';
import { ChatEvent, ChatEventType, ChatMessage } from '../models';
import * as _ from 'lodash';
import { EndpointService } from '../../general/services';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class ChannelService {
  private baseUrl: string;
  constructor(
    endpointService: EndpointService,
    private http: HttpClient
  ) {
    this.baseUrl = endpointService.getChannelsEndpoint();
  }

  createChannel(userId: string) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    const body = JSON.stringify(userId);

    return this.http.post(this.baseUrl, body, { headers });
  }
}
