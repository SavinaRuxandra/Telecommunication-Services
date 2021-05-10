import { Component, OnInit, ViewChild } from '@angular/core';
import { SubscriptionService } from "../shared/subscription.service";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { Sort} from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { SubscriptionDeleteComponent } from "../subscription-delete/subscription-delete.component";
import { FormControl } from "@angular/forms";
import { MAT_TOOLTIP_DEFAULT_OPTIONS, MatTooltipDefaultOptions } from '@angular/material/tooltip';

export const myCustomTooltipDefaults: MatTooltipDefaultOptions = {
  showDelay: 1000,
  hideDelay: 500,
  touchendHideDelay: 1000,
};

@Component({
  selector: 'app-subscription-list',
  templateUrl: './subscription-list.component.html',
  styleUrls: ['./subscription-list.component.css'],
  providers: [
    {provide: MAT_TOOLTIP_DEFAULT_OPTIONS, useValue: myCustomTooltipDefaults}
  ],
})

export class SubscriptionListComponent implements OnInit {

  formSearch = new FormControl();
  dataSource = new MatTableDataSource();

  selectedFiled = 'Type';
  searchFields: string[] = ['Type', 'Duration', 'Price']

  @ViewChild(MatPaginator) paginator!: MatPaginator

  constructor(private subscriptionService: SubscriptionService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.setDataSource();
    this.filterElements();
  }

  setDataSource(): void {
    this.subscriptionService.getSubscriptions()
      .subscribe(subscriptions => {
        this.dataSource.data = subscriptions;
        this.dataSource.paginator = this.paginator;

      });
  }

  filterElements(): void {
    setTimeout(() => this.formSearch.valueChanges.subscribe(string => {
        if(string.length > 0)
          switch (this.selectedFiled) {
            case "Type":
              this.subscriptionService.filterSubscriptionsByType(string).subscribe(subscriptions => this.dataSource.data = subscriptions);
              break;

            case "Duration":
              this.subscriptionService.filterSubscriptionsByDuration(string).subscribe(subscriptions => this.dataSource.data = subscriptions);
              break;

            case "Price":
              this.subscriptionService.filterSubscriptionsByPrice(string).subscribe(subscriptions => this.dataSource.data = subscriptions);
              break;
          }
        else
          this.subscriptionService.getSubscriptions().subscribe(subscriptions => this.dataSource.data = subscriptions);
      })
    );
  }

  sortData(sort: Sort) {
    if (!sort.active || sort.direction === '') {
      return;
    }

    switch (sort.active) {
      case 'type':
        this.subscriptionService.sortSubscriptionsByType(sort.direction)
          .subscribe(subscriptions => {
            console.log(subscriptions);
            this.dataSource.data = subscriptions;
          });
        break;
      case 'duration':
        this.subscriptionService.sortSubscriptionsByDuration(sort.direction)
          .subscribe(subscriptions => {
            this.dataSource.data = subscriptions;
          });
        break;
      case 'price':
        this.subscriptionService.sortSubscriptionsByPrice(sort.direction)
          .subscribe(subscriptions => {
            this.dataSource.data = subscriptions;
          });
        break;
      default:
        return;
    }
  }

  openDeleteDialog(id: number) {
    this.dialog.open(SubscriptionDeleteComponent, {
      data:{id: id}
    });
  }

}
