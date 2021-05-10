import { Component, Inject, OnInit } from '@angular/core';
import { ClientService } from "../shared/client.service";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Client } from "../shared/client.model";

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {


  constructor(private clientService: ClientService,
              @Inject(MAT_DIALOG_DATA) private data: Client) { }

  ngOnInit(): void {
  }

  deleteClient(): void {
    this.clientService.deleteClient(this.data.id)
      .subscribe(() => location.reload());
  }
}
