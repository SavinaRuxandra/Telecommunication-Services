import {Component, Input, OnInit} from '@angular/core';
import {Subscription} from "../shared/subscription.model";
import {SubscriptionService} from "../shared/subscription.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-subscription-add',
  templateUrl: './subscription-add.component.html',
  styleUrls: ['./subscription-add.component.css']
})
export class SubscriptionAddComponent implements OnInit {

  subscription: Subscription = new Subscription;

  constructor(private subscriptionService: SubscriptionService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addSubscription(): void {
    const subscription = <Subscription>{
      type:this.subscription.type,
      duration:this.subscription.duration,
      price:this.subscription.price
    }
    this.subscriptionService.addSubscription(subscription).subscribe(() => {
      this.router.navigate(['/subscriptions']);
    })
  }

}
