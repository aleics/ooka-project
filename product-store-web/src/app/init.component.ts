import { Component } from '@angular/core';
import { StorageService } from './general/services';
import { Router } from '@angular/router';

@Component({
  selector: 'ps-init',
  templateUrl: './init.component.html',
  styleUrls: ['./init.component.styl']
})
export class InitComponent {
  constructor(
    private router: Router,
    private storageService: StorageService
  ) {}

  logout() {
    this.storageService.removeToken();
    this.router.navigate(['/login']);
  }
}
