import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {SubscriptionService} from "../shared/subscription.service";
import {map} from "rxjs/operators";
import {Subscription} from "../shared/subscription.model";

@Component({
  selector: 'app-subscription-update',
  templateUrl: './subscription-update.component.html',
  styleUrls: ['./subscription-update.component.css']
})
export class SubscriptionUpdateComponent implements OnInit {

  formGroup!: FormGroup;

  constructor(private subscriptionService: SubscriptionService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      id:  [{value: 'id', disabled:true}],
      type: new FormControl('', [Validators.required]),
      duration: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required])
    })

    this.activatedRoute.paramMap.pipe(map(() => window.history.state)).subscribe(subscription => {
      this.formGroup.get("id").setValue(subscription.id);
      this.formGroup.controls["type"].setValue(subscription.type);
      this.formGroup.controls["duration"].setValue(subscription.duration);
      this.formGroup.controls["price"].setValue(subscription.price)
    });
  }

  updateSubscription() {
    const subscription = <Subscription> {
      id:this.formGroup.get("id").value,
      type:this.formGroup.get("type").value,
      duration:this.formGroup.get("duration").value,
      price:this.formGroup.get("price").value
    }

    this.subscriptionService.updateSubscription(subscription)
      .subscribe(() => this.router.navigate(['/subscriptions']));
  }

}
