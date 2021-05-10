import {Component, Inject, OnInit} from '@angular/core';
import {ContractService} from "../shared/contract.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Contract} from "../shared/contract.model";

@Component({
  selector: 'app-contract-delete',
  templateUrl: './contract-delete.component.html',
  styleUrls: ['./contract-delete.component.css']
})
export class ContractDeleteComponent implements OnInit {

  constructor(private contractService: ContractService,
              @Inject(MAT_DIALOG_DATA) private data: Contract) { }

  ngOnInit(): void {
  }

  deleteContract(): void {
    this.contractService.deleteContract(this.data.id)
      .subscribe(() => location.reload());
  }
}
