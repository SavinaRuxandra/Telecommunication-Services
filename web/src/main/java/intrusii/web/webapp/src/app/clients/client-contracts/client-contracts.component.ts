import {Component, OnInit, ViewChild} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {FormControl} from "@angular/forms";
import {Contract} from "../../contracts/shared/contract.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Client} from "../shared/client.model";

@Component({
  selector: 'app-client-contracts',
  templateUrl: './client-contracts.component.html',
  styleUrls: ['./client-contracts.component.css']
})
export class ClientContractsComponent implements OnInit {

  formFilter = new FormControl();
  selectedContract!: Contract;
  dataSource = new MatTableDataSource();
  client: Client

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.pipe(map(() => window.history.state))
      .subscribe(client => {
        this.client = client
      })
    this.getContracts();
    setTimeout(() => this.formFilter.valueChanges.subscribe(string => this.dataSource.filter = string));
  }

  getContracts(): void {
    this.clientService.getContractsOfClient(this.client.id).subscribe(contracts => {
      this.dataSource.data = contracts;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        // @ts-ignore
        return data.client.name.toLowerCase().includes(filter.toLowerCase()) || data.subscription.type.toLowerCase().includes(filter.toLowerCase())
      }
    })
  }

  onSelect(contract: Contract): void {
    this.selectedContract = contract;
  }

}
