import {Component, Inject, Input, OnInit} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ClientAddComponent} from "../client-add/client-add.component";
import {Client} from "../shared/client.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {


  constructor(private clientService: ClientService,
              private dialogRef: MatDialogRef<ClientAddComponent>,
              @Inject(MAT_DIALOG_DATA) private data: Client,
              private router: Router) { }

  ngOnInit(): void {
  }

  deleteClient(): void {
    this.clientService.deleteClient(this.data.id);
    location.reload();
  }
}
