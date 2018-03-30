import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'ps-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.styl']
})
export class TopbarComponent {
  @Output()
  public toggleSidebar: EventEmitter<void> = new EventEmitter();

  @Output()
  public logout: EventEmitter<void> = new EventEmitter();
}
