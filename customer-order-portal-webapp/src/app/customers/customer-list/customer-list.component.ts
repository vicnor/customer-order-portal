import { DataService } from './../../data.service';
import { Customer } from './../customer';
import { Component, OnInit } from '@angular/core';    

@Component({
  selector: 'customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Customer[];

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.sendGetRequest("/customers").subscribe((data: Customer[])=>{
      console.log(data);
      this.customers = data;
    })
  }

}
