import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SubscriptionService} from "../shared/subscription.service";
import {Subscription} from "../shared/subscription.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-subscription-delete',
  templateUrl: './subscription-delete.component.html',
  styleUrls: ['./subscription-delete.component.css']
})
export class SubscriptionDeleteComponent implements OnInit {

  constructor(private subscriptionService: SubscriptionService,
              @Inject(MAT_DIALOG_DATA) private data: Subscription,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
  }

  deleteSubscription(): void {
    this.subscriptionService.deleteSubscription(this.data.id)
      .subscribe(() => {
        location.reload();
        this.snack.open("Subscription successfully deleted", "x", {duration: 4000});
      });
  }

}
