import { Component, ViewChild, ElementRef, OnDestroy, AfterViewInit } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../../../general/services';
import { Product, AllProductsFilter } from '../../../general/models/index';
// tslint:disable-next-line:import-blacklist
import { Observable, Subscription } from 'rxjs';
import * as _ from 'lodash';

@Component({
  selector: 'ps-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.styl']
})
export class HomeComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild('searchComponent') searchComponent: ElementRef;

  isLoading = true;
  products: Product[] = [];
  searchSubscription: Subscription;

  constructor(
    private router: ActivatedRoute,
    private productsService: ProductsService
  ) {
  }

  ngOnInit() {
    // single http request will trigger once, that's why there's no
    // need to unsubscribe
    this.getProducts()
      .subscribe((products) => this.onRequestDone(products));
  }

  ngAfterViewInit() {
    this.searchSubscription = Observable
      .fromEvent(this.searchComponent.nativeElement, 'keyup')
      .debounceTime(200)
      .distinctUntilChanged()
      .map(() => this.searchComponent.nativeElement.value)
      .flatMap((q: string) => {
        this.isLoading = true;
        return this.getProducts(q);
      })
      .subscribe((products) => this.onRequestDone(products));
  }

  ngOnDestroy() {
    this.searchSubscription.unsubscribe();
  }

  private getProducts(q: string = null) {
    const filter: AllProductsFilter = !_.isEmpty(q) ? { q } : null;
    return this.productsService.getProducts(filter);
  }

  private onRequestDone(products: Product[]) {
    this.products = products || [];
    this.isLoading = false;
  }
}
