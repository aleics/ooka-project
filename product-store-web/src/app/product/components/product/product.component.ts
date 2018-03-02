import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../../../general/services';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../../general/models';

@Component({
  selector: 'ps-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.styl']
})
export class ProductComponent implements OnInit {
  public isLoading = true;

  private product: Product;

  constructor(
    private productsService: ProductsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const productId = this.route.snapshot.params['productId'];
    this.productsService
      .getProduct({ id: productId })
      .subscribe((product) => {
        this.product = product;
        this.isLoading = false;
      });
  }
}
