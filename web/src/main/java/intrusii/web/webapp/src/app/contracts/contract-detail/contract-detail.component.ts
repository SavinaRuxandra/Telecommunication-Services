import {Component, Input, OnInit} from '@angular/core';
import {Contract} from "../shared/contract.model";
import {Client} from "../../clients/shared/client.model";
import {ClientService} from "../../clients/shared/client.service";
import {SubscriptionService} from "../../subscriptions/shared/subscription.service";
import {Subscription} from "../../subscriptions/shared/subscription.model";

@Component({
  selector: 'app-contract-detail',
  templateUrl: './contract-detail.component.html',
  styleUrls: ['./contract-detail.component.css']
})
export class ContractDetailComponent implements OnInit {

  @Input() contract!: Contract;
  client!: Client;
  subscription!: Subscription;

  constructor(private clientService: ClientService,
              private subscriptionService: SubscriptionService) {
  }

  ngOnInit(): void {
  }

  getClientById(): void {
     this.clientService.getClientById(this.contract.clientId).subscribe(client => this.client = client);
  }

  getSubscriptionById(): void {
    this.subscriptionService.getSubscriptionById(this.contract.subscriptionId).subscribe(subscription => this.subscription = subscription);
  }


}
