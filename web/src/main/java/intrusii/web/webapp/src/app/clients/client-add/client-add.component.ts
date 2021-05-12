import { Component, Input, OnInit } from '@angular/core';
import { Client } from "../shared/client.model";
import { ClientService } from "../shared/client.service";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators
} from "@angular/forms";
import {Router} from "@angular/router";
import {IDCard} from "../../idCards/shared/idCard.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {

  @Input() client!: Client;
  formGroup!: FormGroup;

  constructor(private clientService: ClientService,
              private formBuilder: FormBuilder,
              private router: Router,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      cnp: new FormControl('', [Validators.required]),
      nationality: new FormControl('', [Validators.required]),
      placeOfBirth: new FormControl(''),
      residence: new FormControl(''),
      sex: new FormControl(''),
      series: new FormControl('', [Validators.required]),
      number: new FormControl('', [Validators.required])
    })
  }

  addClient(): void {
    const client = <Client> {
      idCard: <IDCard>{
        cnp: this.formGroup.controls["cnp"].value,
        nationality: this.formGroup.controls["nationality"].value,
        placeOfBirth: this.formGroup.controls["placeOfBirth"].value,
        residence: this.formGroup.controls["residence"].value,
        sex: this.formGroup.controls["sex"].value,
        series: this.formGroup.controls["series"].value,
        number: this.formGroup.controls["number"].value
      },
      name: this.formGroup.controls["name"].value,
      email: this.formGroup.controls["email"].value
    }
    this.clientService.addClient(client)
      .subscribe(() => this.router.navigate(['/clients'])
        .then(() => this.snack.open("Client successfully added", "x", {duration: 4000})));
  }
}
