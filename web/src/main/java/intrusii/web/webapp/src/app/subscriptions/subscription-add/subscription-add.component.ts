import {Component, Input, OnInit} from '@angular/core';
import {Subscription} from "../shared/subscription.model";
import {SubscriptionService} from "../shared/subscription.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-subscription-add',
  templateUrl: './subscription-add.component.html',
  styleUrls: ['./subscription-add.component.css']
})
export class SubscriptionAddComponent implements OnInit {

  subscription: Subscription = new Subscription;

  constructor(private subscriptionService: SubscriptionService,
              private router: Router,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
  }

  addSubscription(): void {
    const subscription = <Subscription>{
      type:this.subscription.type,
      duration:this.subscription.duration,
      price:this.subscription.price
    }
    this.subscriptionService.addSubscription(subscription)
      .subscribe(() => {
        this.router.navigate(['/subscriptions'])
          .then(() => this.snack.open("Subscription successfully added", "x", {duration: 4000}))
    })
  }

}
