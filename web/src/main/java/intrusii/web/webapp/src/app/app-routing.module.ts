import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent}  from "./clients/clients.component";
import { SubscriptionsComponent } from "./subscriptions/subscriptions.component";
import { ContractsComponent } from "./contracts/contracts.component";
import { ClientAddComponent } from "./clients/client-add/client-add.component";
import { ClientUpdateComponent } from "./clients/client-update/client-update.component";
import { SubscriptionAddComponent } from "./subscriptions/subscription-add/subscription-add.component";
import { SubscriptionUpdateComponent } from "./subscriptions/subscription-update/subscription-update.component";
import { ContractAddComponent } from "./contracts/contract-add/contract-add.component";
import { ContractUpdateComponent } from "./contracts/contract-update/contract-update.component";
import { IdCardDetailComponent } from "./idCards/id-card-detail/id-card-detail.component";

const routes: Routes = [
  {path: 'clients', component: ClientsComponent},
  {path: 'clients/add', component: ClientAddComponent},
  {path: 'clients/update', component: ClientUpdateComponent},
  {path: 'clients/idCard/details', component: IdCardDetailComponent},

  {path: 'subscriptions', component: SubscriptionsComponent},
  {path: 'subscriptions/add', component: SubscriptionAddComponent},
  {path: 'subscriptions/update', component: SubscriptionUpdateComponent},

  {path: 'contracts', component: ContractsComponent},
  {path: 'contracts/add', component: ContractAddComponent},
  {path: 'contracts/update', component: ContractUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
