import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Subscription} from "./subscription.model";

import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class SubscriptionService {
  private subscriptionUrl = 'http://localhost:8080/api/subscriptions';

  constructor(private httpClient: HttpClient) { }

  getSubscriptions(): Observable<Subscription[]> {
    return this.httpClient
      .get<Array<Subscription>>(this.subscriptionUrl);
  }

  getSubscriptionById(id: number): Observable<Subscription> {
    return this.httpClient
      .post<Subscription>(this.subscriptionUrl + "/byId", id);
  }
}
