import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../../../general/services';
import { AuthService } from '../../../login/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../../general/models';

@Component({
  selector: 'ps-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.styl']
})
export class ProductComponent implements OnInit {
  public isLoading = true;
  public chatAvailable = false;

  private product: Product;

  constructor(
    private productsService: ProductsService,
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    const productId = this.route.snapshot.params['productId'];
    this.productsService
      .getProduct({ id: productId })
      .subscribe((product) => {
        this.product = product;
        this.isLoading = false;
      });

    this.chatAvailable = this.authService.chatAvailable();
  }

  goToChat() {
    this.router.navigate(['chat', { product: this.product.id }]);
  }
}
