import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.styl']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;

  constructor() { }

  ngOnInit() {
      this.loginForm = new FormGroup({
      password: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required)
    });
  }

  onSubmit(username: string, pw: string) {
    console.log('Name' + this.loginForm.get('username') + ' PW: ' + this.loginForm.get('password'));
  }

}
