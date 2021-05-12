import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ContractService } from "../shared/contract.service";
import { ActivatedRoute, Router } from "@angular/router";
import { map } from "rxjs/operators";
import { Contract } from "../shared/contract.model";
import { Client } from "../../clients/shared/client.model";
import { Subscription } from "../../subscriptions/shared/subscription.model";
import {formatDate} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-contract-update',
  templateUrl: './contract-update.component.html',
  styleUrls: ['./contract-update.component.css']
})
export class ContractUpdateComponent implements OnInit {

  formGroup!: FormGroup;

  constructor(private contractService: ContractService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private snack : MatSnackBar) {
  }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      id: [{value: 'id', disabled: true}],
      clientId: new FormControl('', [Validators.required]),
      subscriptionId: [{value: 'subscriptionId', disabled: true}],
      address: new FormControl('', [Validators.required]),
      date: new FormControl('', [Validators.required])
    })

    this.activatedRoute.paramMap.pipe(map(() => window.history.state))
      .subscribe(contract => {
        this.formGroup.get("id").setValue(contract.id);
        this.formGroup.get("clientId").setValue(contract.client.id);
        this.formGroup.get("subscriptionId").setValue(contract.subscription.id);
        this.formGroup.get("address").setValue(contract.address);
        this.formGroup.get("date").setValue(new Date(contract.date));
      });
  }

  updateContract(): void {
    const client = <Client> {
      id: this.formGroup.get("clientId").value
    }

    const subscription = <Subscription> {
      id: this.formGroup.get("subscriptionId").value
    }

    console.log(formatDate(this.formGroup.get("date").value, 'yyyy-MM-dd', 'en-US'))
    const contract = <Contract><unknown>{
      id: this.formGroup.get("id").value,
      client: client,
      subscription: subscription,
      address: this.formGroup.get("address").value,
      date: formatDate(this.formGroup.get("date").value, 'yyyy-MM-dd', 'en-US')
    }

    this.contractService.updateContract(contract)
      .subscribe(() => this.router.navigate(['/contracts'])
        .then(() => this.snack.open("Contract successfully updated", "x", {duration: 4000})));
  }

}
