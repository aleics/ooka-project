import { Component } from '@angular/core';

@Component({
  selector: 'ps-spinner',
  templateUrl: './spinner.component.html'
})
export class SpinnerComponent {
  public options = {
    mode: 'indeterminate',
    color: 'primary',
    diameter: 35
  };
}
