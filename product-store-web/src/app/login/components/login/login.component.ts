import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.styl']
})
export class LoginComponent implements OnInit {

  constructor() { }
  submitted = false;
  ngOnInit() {
  }

  onSubmit() { this.submitted = true; }

}
