import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { ProductsService } from '../../general/services';
import { Product } from '../../general/models';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

@Injectable()
export class ChatProductResolver implements Resolve<Product> {

  constructor(private productsService: ProductsService) {}

  resolve(route: ActivatedRouteSnapshot) {
    const productId = route.paramMap.get('product');
    if (productId) {
      return this.productsService.getProduct({ id: productId });
    }
    return Observable.of(null);
  }
}
