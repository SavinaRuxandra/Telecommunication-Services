import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientsComponent } from './clients/clients.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import { HttpClientModule } from "@angular/common/http";
import { ClientListComponent } from './clients/client-list/client-list.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { SubscriptionListComponent } from './subscriptions/subscription-list/subscription-list.component';
import { ContractsComponent } from './contracts/contracts.component';
import { ContractListComponent } from './contracts/contract-list/contract-list.component';
import { ContractDetailComponent } from './contracts/contract-detail/contract-detail.component';
import {ClientService} from "./clients/shared/client.service";
import {SubscriptionService} from "./subscriptions/shared/subscription.service";
import {ContractService} from "./contracts/shared/contract.service";

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    ClientDetailComponent,
    ClientListComponent,
    SubscriptionsComponent,
    SubscriptionListComponent,
    ContractsComponent,
    ContractListComponent,
    ContractDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ClientService, SubscriptionService, ContractService],
  bootstrap: [AppComponent]
})
export class AppModule { }
