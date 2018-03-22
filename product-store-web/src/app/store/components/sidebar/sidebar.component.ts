import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AccountType } from '../../../general/models';

@Component({
  selector: 'ps-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.styl']
})
export class SidebarComponent {
  @Input() chatAvailable: boolean;

  constructor(
    private router: Router
  ) {}

  public go(section: string) {
    this.router.navigate([section]);
  }
}
