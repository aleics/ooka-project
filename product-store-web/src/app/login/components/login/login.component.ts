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
  submitted = false;
  ngOnInit() {
      this.loginForm = new FormGroup({
      password: new FormControl('', [<any>Validators.required]),
      username: new FormControl('', [<any>Validators.required])
    });
  }

  onSubmit(username: string, pw: string) {
    console.log('Name' + this.loginForm.get('username') + ' PW: ' + this.loginForm.get('password'));
    this.submitted = true;
  }

}
