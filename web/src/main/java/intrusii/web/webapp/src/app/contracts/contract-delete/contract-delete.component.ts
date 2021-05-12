import {Component, Inject, OnInit} from '@angular/core';
import {ContractService} from "../shared/contract.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Contract} from "../shared/contract.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-contract-delete',
  templateUrl: './contract-delete.component.html',
  styleUrls: ['./contract-delete.component.css']
})
export class ContractDeleteComponent implements OnInit {

  constructor(private contractService: ContractService,
              @Inject(MAT_DIALOG_DATA) private data: Contract,
              private snack : MatSnackBar) { }

  ngOnInit(): void {
  }

  deleteContract(): void {
    this.contractService.deleteContract(this.data.id)
      .subscribe(() => {
        location.reload();
        this.snack.open("Client successfully added", "x", {duration: 4000});
      });
  }
}
