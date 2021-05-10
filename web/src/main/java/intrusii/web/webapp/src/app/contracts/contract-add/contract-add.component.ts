import { Component, Input, OnInit } from '@angular/core';
import { Contract } from "../shared/contract.model";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ContractService } from "../shared/contract.service";
import { Router } from "@angular/router";
import { formatDate } from "@angular/common";
import { Client } from "../../clients/shared/client.model";
import { Subscription } from "../../subscriptions/shared/subscription.model";

@Component({
  selector: 'app-contract-add',
  templateUrl: './contract-add.component.html',
  styleUrls: ['./contract-add.component.css']
})
export class ContractAddComponent implements OnInit {

  @Input() contract!: Contract;
  formGroup!: FormGroup

  constructor(private contractService: ContractService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      client: new FormControl('', [Validators.required]),
      subscription: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      date: new FormControl('', [Validators.required])
    })
  }

  addContract(): void {
    const contract = <Contract><unknown>{
      client: <Client>{
        id: this.formGroup.controls['client'].value
      },
      subscription: <Subscription>{
        id: this.formGroup.controls['subscription'].value,
      },
      address: this.formGroup.controls['address'].value,
      date: formatDate(this.formGroup.controls['date'].value, 'yyyy-MM-dd', 'en-US')
    }

    this.contractService.addContract(contract)
      .subscribe(
        () => this.router.navigate(['/contracts'])
      );
  }

}
