import { Component, Input } from '@angular/core';
import { Product } from '../../../general/models';

@Component({
  selector: 'ps-products-list',
  templateUrl: './products-list.component.html'
})
export class ProductsListComponent {
  @Input()
  public products: Product[];
}