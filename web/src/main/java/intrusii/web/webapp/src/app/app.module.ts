import { NgModule } from '@angular/core';

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
import { ClientService } from "./clients/shared/client.service";
import { SubscriptionService } from "./subscriptions/shared/subscription.service";
import { ContractService } from "./contracts/shared/contract.service";
import { ClientAddComponent } from './clients/client-add/client-add.component';
import { MatInputModule } from "@angular/material/input";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatTableModule } from "@angular/material/table";
import { MatSortModule } from "@angular/material/sort";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatCardModule } from "@angular/material/card";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatTabsModule } from "@angular/material/tabs";
import { MatListModule } from "@angular/material/list";
import { ClientDeleteComponent } from './clients/client-delete/client-delete.component';
import { MatDialogModule } from "@angular/material/dialog";
import { ClientUpdateComponent } from './clients/client-update/client-update.component';
import { SubscriptionDeleteComponent } from './subscriptions/subscription-delete/subscription-delete.component';
import { SubscriptionAddComponent } from './subscriptions/subscription-add/subscription-add.component';
import { SubscriptionUpdateComponent } from './subscriptions/subscription-update/subscription-update.component';
import {MatRadioGroup, MatRadioModule} from "@angular/material/radio";

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
    ClientAddComponent,
    ClientDeleteComponent,
    ClientUpdateComponent,
    SubscriptionDeleteComponent,
    SubscriptionAddComponent,
    SubscriptionUpdateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatListModule,
    MatDialogModule,
    MatRadioModule,
  ],
  providers: [ClientService, SubscriptionService, ContractService],
  bootstrap: [AppComponent]
})
export class AppModule { }
