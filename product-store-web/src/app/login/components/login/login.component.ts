import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { Credentials, TokenData } from '../../models';
import { StorageService } from '../../../general/services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.styl']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      password: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required)
    });
  }

  login() {
    const credentials: Credentials = this.loginForm.value;
    this.loginService.login(credentials)
      .subscribe((tokenData: TokenData) => {
        this.storageService.saveTokenData(tokenData);
        this.router.navigate(['/home']);
      });
  }

}
