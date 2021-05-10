import { Component, OnInit } from '@angular/core';
import { IDCard } from "../shared/idCard.model";
import { ActivatedRoute } from "@angular/router";
import { map } from "rxjs/operators";

@Component({
  selector: 'app-id-card-detail',
  templateUrl: './id-card-detail.component.html',
  styleUrls: ['./id-card-detail.component.css']
})
export class IdCardDetailComponent implements OnInit {

  idCard: IDCard

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.pipe(map(() => window.history.state))
      .subscribe(idCard => {
        this.idCard = idCard
      });
  }

}
