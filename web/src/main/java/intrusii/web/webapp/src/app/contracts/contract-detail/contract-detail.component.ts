import {Component, Input, OnInit} from '@angular/core';
import {Contract} from "../shared/contract.model";

@Component({
  selector: 'app-contract-detail',
  templateUrl: './contract-detail.component.html',
  styleUrls: ['./contract-detail.component.css']
})
export class ContractDetailComponent implements OnInit {

  @Input() contract!: Contract;

  constructor() {
  }

  ngOnInit(): void {
  }

  hideDetails(): void {
    location.reload();
  }
}
