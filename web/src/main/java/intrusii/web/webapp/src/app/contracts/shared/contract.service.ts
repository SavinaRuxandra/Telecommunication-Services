import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Contract} from "./contract.model";

import {Observable} from "rxjs";
import {Pair} from "./pair.model";

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private contractsUrl = 'http://localhost:8080/api/contracts';

  constructor(private httpClient: HttpClient) { }

  addContract(contract: Contract): Observable<Contract> {
    return this.httpClient
      .post<Contract>(this.contractsUrl, contract);
  }

  deleteContract(id: number): Observable<Contract> {
    const urlDelete = `${this.contractsUrl}/${id}`;
    return this.httpClient
      .delete<Contract>(urlDelete);
  }

  updateContract(contract: Contract): Observable<Contract> {
    const urlUpdate = `${this.contractsUrl}/${contract.id}`;
    return this.httpClient
      .put<Contract>(urlUpdate, contract);
  }

  getContracts(): Observable<Contract[]> {
    return this.httpClient
      .get<Array<Contract>>(this.contractsUrl);
  }

  filterActiveContracts(): Observable<Contract[]> {
    return this.httpClient
      .get<Array<Contract>>(`${this.contractsUrl}/filterActive`);
  }

  getStatistics(): Observable<Pair[]> {
    return this.httpClient
      .get<Array<Pair>>(`${this.contractsUrl}/statistics`)
  }

}
