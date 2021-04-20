import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Contract} from "./contract.model";

import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private contractsUrl = 'http://localhost:8080/api/contracts';

  constructor(private httpClient: HttpClient) { }

  getContracts(): Observable<Contract[]> {
    return this.httpClient
      .get<Array<Contract>>(this.contractsUrl);
  }
}
