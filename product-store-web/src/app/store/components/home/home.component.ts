import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../../../general/services';
import { Product } from '../../../general/models/index';

@Component({
  selector: 'ps-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.styl']
})
export class HomeComponent implements OnInit {

  query: string;
  isLoading = true;
  products: Product[] = [];

  constructor(
    private router: ActivatedRoute,
    private productsService: ProductsService
  ) {
  }

  ngOnInit() {
    this.query = this.router.snapshot.queryParams['q'];
    this.productsService.getProducts()
      .subscribe((products) => {
        this.products = products;
        this.isLoading = false;
      });
  }

  onSearch() {
    console.log(this.query);
  }
}
