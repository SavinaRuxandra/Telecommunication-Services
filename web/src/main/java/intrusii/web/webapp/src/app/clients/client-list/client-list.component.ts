import { Component, OnInit } from '@angular/core';
import { Client } from "../shared/client.model";
import { ClientService } from "../shared/client.service";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  selectedClient?: Client;
  clients?: Array<Client>;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getClients();
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  getClients(): void {
    this.clientService.getClients().subscribe(clients => this.clients = clients);
  }
}
