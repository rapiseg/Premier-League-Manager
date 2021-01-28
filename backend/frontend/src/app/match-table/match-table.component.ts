import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatchTableService} from '../match-table.service';

//creating a match interface
export interface Match{
  home: string;
  guest: string;
  date: string;
  scoreInFormat: string;
}

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent  implements OnInit{
  headings: string[] = ['date', 'home.name', 'guest.name', 'scoreInFormat'];
  matchList;

  constructor(private service: MatchTableService) {
  }

  //search by the value entered
  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.matchList.filter = filterValue.trim().toUpperCase();
  }

  //assigning the data from the backend
  ngOnInit() {
    this.service.getMatches().subscribe(data =>{
      this.matchList = new MatTableDataSource(data);
    });
  }
}
/*
References
https://material.angular.io/components/table/examples
 */
