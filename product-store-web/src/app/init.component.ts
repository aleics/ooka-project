import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './login/services/auth.service';
import { StorageService } from './general/services';

@Component({
  selector: 'ps-init',
  templateUrl: './init.component.html',
  styleUrls: ['./init.component.styl']
})
export class InitComponent implements OnInit {
  chatAvailable: boolean;

  constructor(
    private router: Router,
    private storageService: StorageService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.chatAvailable = this.authService.chatAvailable();
  }

  logout() {
    this.storageService.removeToken();
    this.router.navigate(['/login']);
  }
}
