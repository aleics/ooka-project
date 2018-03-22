import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

import * as _ from 'lodash';


@Injectable()
export class ChatGuardService implements CanActivate {
  constructor(
    private router: Router
  ) {}

  canActivate(): boolean {
    // TODO: check permissions

    // create channels
    return true;
  }
}
