import { Component, OnInit, ViewChild } from '@angular/core';
import { Client } from "../shared/client.model";
import { ClientService } from "../shared/client.service";
import { MatSort } from "@angular/material/sort";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { MatDialog } from "@angular/material/dialog";
import { ClientDeleteComponent } from "../client-delete/client-delete.component";
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})

export class ClientListComponent implements OnInit {

  formFilter = new FormControl();
  selectedClient!: Client;
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private clientService: ClientService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getClients();

    setTimeout(() => this.formFilter.valueChanges.subscribe(string => this.dataSource.filter = string));
  }

  getClients(): void {
    this.clientService.getClients()
      .subscribe(clients => {
        this.dataSource.data = clients;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filter = "";
      });
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  openDeleteDialog(id: number) {
    this.dialog.open(ClientDeleteComponent, {
      data:{id: id}
    });
  }
}

