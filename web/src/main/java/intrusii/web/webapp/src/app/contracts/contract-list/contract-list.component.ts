import { Component, OnInit, ViewChild } from '@angular/core';
import { ContractService } from "../shared/contract.service";
import { Contract } from "../shared/contract.model";
import { FormControl } from "@angular/forms";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { ContractDeleteComponent } from "../contract-delete/contract-delete.component";

@Component({
  selector: 'app-contract-list',
  templateUrl: './contract-list.component.html',
  styleUrls: ['./contract-list.component.css']
})

export class ContractListComponent implements OnInit {

  formFilter = new FormControl();
  selectedContract!: Contract;
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private contractService: ContractService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getContracts();

    setTimeout(() => this.formFilter.valueChanges.subscribe(string => this.dataSource.filter = string));
  }

  getContracts(): void {
    this.contractService.getContracts()
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

  openDeleteDialog(id: number) {
    this.dialog.open(ContractDeleteComponent, {
      data:{id: id}
    });
  }
}
