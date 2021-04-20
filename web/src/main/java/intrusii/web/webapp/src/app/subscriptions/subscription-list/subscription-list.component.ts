import { Component, OnInit } from '@angular/core';
import {Subscription} from "../shared/subscription.model";
import {SubscriptionService} from "../shared/subscription.service";

@Component({
  selector: 'app-subscription-list',
  templateUrl: './subscription-list.component.html',
  styleUrls: ['./subscription-list.component.css']
})
export class SubscriptionListComponent implements OnInit {

  subscriptions?: Array<Subscription>

  constructor(private subscriptionService: SubscriptionService) { }

  ngOnInit(): void {
    this.getSubscriptions();
  }

  getSubscriptions(): void {
    this.subscriptionService.getSubscriptions().subscribe(subscriptions => this.subscriptions = subscriptions);
  }

}
