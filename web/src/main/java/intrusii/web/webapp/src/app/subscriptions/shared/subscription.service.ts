import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Subscription} from "./subscription.model";

import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class SubscriptionService {
  private subscriptionsUrl = 'http://localhost:8080/api/subscriptions';

  constructor(private httpClient: HttpClient) { }

  addSubscription(subscription: Subscription): Observable<Subscription> {
    return this.httpClient
      .post<Subscription>(this.subscriptionsUrl, subscription);
  }

  deleteSubscription(id: number): Observable<Subscription> {
    return this.httpClient
      .delete<Subscription>(`${this.subscriptionsUrl}/${id}`);
  }

  updateSubscription(subscription: Subscription): Observable<Subscription> {
    return this.httpClient
      .put<Subscription>(`${this.subscriptionsUrl}/${subscription.id}`, subscription);
  }

  getSubscriptions(): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(this.subscriptionsUrl);
  }

  sortSubscriptionsByType(isAsc: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/sortByType/${isAsc}`);
  }

  sortSubscriptionsByDuration(isAsc: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/sortByDuration/${isAsc}`);
  }

  sortSubscriptionsByPrice(isAsc: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/sortByPrice/${isAsc}`);
  }

  filterSubscriptionsByType(type: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/filterByType/${type}`);
  }

  filterSubscriptionsByDuration(duration: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/filterByDuration/${duration}`);
  }

  filterSubscriptionsByPrice(price: string): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(`${this.subscriptionsUrl}/filterByPrice/${price}.`);
  }
}
