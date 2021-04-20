import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {SubscriptionsComponent} from "./subscriptions/subscriptions.component";
import {ContractsComponent} from "./contracts/contracts.component";
import {ContractDetailComponent} from "./contracts/contract-detail/contract-detail.component";

const routes: Routes = [
//{path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'clients', component: ClientsComponent},
  {path: 'clients/detail/', component: ClientDetailComponent},

  {path: 'subscriptions', component: SubscriptionsComponent},

  {path: 'contracts', component: ContractsComponent},
  {path: 'contracts/detail/', component: ContractDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
