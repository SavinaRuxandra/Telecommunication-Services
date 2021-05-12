import { Component, OnInit } from '@angular/core';
import { ContractService } from "../shared/contract.service";

@Component({
  selector: 'app-contract-statistic',
  templateUrl: './contract-statistics.component.html',
  styleUrls: ['./contract-statistics.component.css']
})
export class ContractStatisticsComponent implements OnInit {

  chartLabels : string[] = [];
  chartData : number[] = []
  chartColors: any[] = [
    {
      backgroundColor:["#e25669",
        "#e28956",
        "#e2cf56",
        "#afe256",
        "#68e256",
        "#56e289",
        "#56e2cf",
        "#56afe2",
        "#5669e2",
        "#8a56e2",
        "#cf56e2",
        "#e256ae",]
    }];
  chartType = 'pie';

  constructor(private contractService: ContractService) {}

  ngOnInit() {
    this.contractService.getStatistics()
      .subscribe(statistics => {
        statistics.forEach(e => {this.chartLabels.push(e.first); this.chartData.push(e.second)})
      })
  }
}
