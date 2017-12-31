import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'ps-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.styl']
})
export class SidebarComponent {
  constructor(
    private router: Router
  ) {}

  public go(section: string) {
    this.router.navigate([section]);
  }
}
