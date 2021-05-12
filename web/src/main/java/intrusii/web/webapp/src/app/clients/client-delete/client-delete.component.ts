import { Component, Inject, OnInit } from '@angular/core';
import { ClientService } from "../shared/client.service";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Client } from "../shared/client.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {


  constructor(private clientService: ClientService,
              @Inject(MAT_DIALOG_DATA) private data: Client,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
  }

  deleteClient(): void {
    this.clientService.deleteClient(this.data.id)
      .subscribe(() => {location.reload();
       this.snack.open("Client successfully deleted", "x", {duration: 4000})}
       );
  }
}
