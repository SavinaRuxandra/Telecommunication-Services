import { Component, OnInit } from '@angular/core';
import { ClientService } from "../shared/client.service";
import { ActivatedRoute, Router } from "@angular/router";
import { map } from "rxjs/operators";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Client } from "../shared/client.model";
import { IDCard } from "../../idCards/shared/idCard.model";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnInit {

  formGroup!: FormGroup;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      id:  [{value: 'id', disabled: true}],
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

    this.activatedRoute.paramMap.pipe(map(() => window.history.state))
      .subscribe(client => {
      this.formGroup.get("id").setValue(client.id);
      this.formGroup.controls["name"].setValue(client.name);
      this.formGroup.controls["email"].setValue(client.email);
      this.formGroup.controls["cnp"].setValue(client.idCard.cnp);
      this.formGroup.controls["nationality"].setValue(client.idCard.nationality);
      this.formGroup.controls["placeOfBirth"].setValue(client.idCard.placeOfBirth);
      this.formGroup.controls["residence"].setValue(client.idCard.residence);
      this.formGroup.controls["sex"].setValue(client.idCard.sex);
      this.formGroup.controls["series"].setValue(client.idCard.series);
      this.formGroup.controls["number"].setValue(client.idCard.number);
    });
  }

  updateClient() {
    const client = <Client>{
      id:this.formGroup.get("id").value,
      idCard: <IDCard>{
        cnp: this.formGroup.controls["cnp"].value,
        nationality: this.formGroup.controls["nationality"].value,
        placeOfBirth: this.formGroup.controls["placeOfBirth"].value,
        residence: this.formGroup.controls["residence"].value,
        sex: this.formGroup.controls["sex"].value,
        series: this.formGroup.controls["series"].value,
        number: this.formGroup.controls["number"].value
      },
      name:this.formGroup.get("name").value,
      email:this.formGroup.get("email").value,
    }

    this.clientService.updateClient(client)
      .subscribe(() => this.router.navigate(['/clients'])
        .then(() => this.snack.open("Client successfully updated", "x", {duration: 4000})));
  }

}
