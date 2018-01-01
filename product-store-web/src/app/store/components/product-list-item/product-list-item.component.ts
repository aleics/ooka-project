import { Component, Input } from '@angular/core';
import { Product } from '../../../general/models';

@Component({
  selector: 'ps-product-list-item',
  templateUrl: './product-list-item.component.html',
  styleUrls: ['./product-list-item.component.styl']
})
export class ProductListItemComponent {
  @Input()
  public product: Product;
}