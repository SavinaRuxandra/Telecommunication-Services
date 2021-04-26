import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {SubscriptionsComponent} from "./subscriptions/subscriptions.component";
import {ContractsComponent} from "./contracts/contracts.component";
import {ContractDetailComponent} from "./contracts/contract-detail/contract-detail.component";
import {ClientAddComponent} from "./clients/client-add/client-add.component";
import {ClientUpdateComponent} from "./clients/client-update/client-update.component";

const routes: Routes = [
  {path: 'clients', component: ClientsComponent},
  {path: 'clients/add', component: ClientAddComponent},
  {path: 'clients/update', component: ClientUpdateComponent},

  {path: 'subscriptions', component: SubscriptionsComponent},

  {path: 'contracts', component: ContractsComponent},
  {path: 'contracts/detail', component: ContractDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
