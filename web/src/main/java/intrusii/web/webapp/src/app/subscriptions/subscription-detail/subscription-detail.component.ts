import {Component, Input, OnInit} from '@angular/core';
import {Subscription} from "../shared/subscription.model";

@Component({
  selector: 'app-subscription-detail',
  templateUrl: './subscription-detail.component.html',
  styleUrls: ['./subscription-detail.component.css']
})
export class SubscriptionDetailComponent implements OnInit {

  @Input() subscription!: Subscription

  constructor() { }

  ngOnInit(): void {
  }

}
