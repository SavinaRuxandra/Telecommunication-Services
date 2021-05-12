import { Component, OnInit, ViewChild } from '@angular/core';
import { Contract } from "../shared/contract.model";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { ContractService } from "../shared/contract.service";
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-contract-active-contracts',
  templateUrl: './contract-active.component.html',
  styleUrls: ['./contract-active.component.css']
})
export class ContractActiveComponent implements OnInit {

  formFilter = new FormControl();
  selectedContract!: Contract;
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private contractService: ContractService) {
  }

  ngOnInit(): void {
    this.getFilteredContracts();
    setTimeout(() => this.formFilter.valueChanges.subscribe(string => this.dataSource.filter = string));
  }

  getFilteredContracts(): void {
    this.contractService.filterActiveContracts()
      .subscribe(contracts => {
        this.dataSource.data = contracts;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filterPredicate = function(data, filter): boolean {
          // @ts-ignore
          return data.client.name.toLowerCase().includes(filter.toLowerCase()) || data.subscription.type.toLowerCase().includes(filter.toLowerCase())
        }
      });
  }

  onSelect(contract: Contract): void {
    this.selectedContract = contract;
  }
}

