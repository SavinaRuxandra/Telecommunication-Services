import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Client} from "./client.model";

import {Observable} from "rxjs";
import {Contract} from "../../contracts/shared/contract.model";

@Injectable({
  providedIn: 'root'
})

export class ClientService {
  private clientsUrl = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) { }

  addClient(client: Client): Observable<Client> {
    return this.httpClient
      .post<Client>(this.clientsUrl, client);
  }

  deleteClient(id: number): Observable<Client> {
    const urlDelete = `${this.clientsUrl}/${id}`;
    return this.httpClient
      .delete<Client>(urlDelete);
  }

  updateClient(client: Client): Observable<Client> {
    const urlUpdate = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(urlUpdate, client);
  }

  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  getContractsOfClient(id: number): Observable<Contract[]> {
    return this.httpClient
      .get<Array<Contract>>(`${this.clientsUrl}/contracts/${id}`);
  }
}
