import { Component, OnInit } from '@angular/core';
import {ContractService} from "../shared/contract.service";
import {Contract} from "../shared/contract.model";

@Component({
  selector: 'app-contract-list',
  templateUrl: './contract-list.component.html',
  styleUrls: ['./contract-list.component.css']
})
export class ContractListComponent implements OnInit {

  contracts?: Array<Contract>

  constructor(private contractService: ContractService) { }

  ngOnInit(): void {
    this.getSubscriptions();
  }

  getSubscriptions(): void {
    this.contractService.getContracts().subscribe(contracts => this.contracts = contracts);
  }

}
