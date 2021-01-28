import {Component, OnInit, ViewChild} from '@angular/core';
import {LeagueTableService} from '../league-table.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

// Creates an interface FootballClub to get data from backend
export interface FootballClub {
  name: string;
  noOfMatchesPlayed: number;
  win: number;
  draw: number;
  defeat: number;
  noOfGoalsScored: number;
  noOfGoalsReceived: number;
  goalDifference: number;
  points: number;
}

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit{

  headings: string[] = ['position', 'name', 'noOfMatchesPlayed', 'win', 'draw', 'defeat', 'noOfGoalsScored', 'noOfGoalsReceived', 'goalDifference', 'points'];
  clubs;

  // tslint:disable-next-line:variable-name
  constructor(private _service: LeagueTableService) {
  }
  //gets the data from the backend and assigns it into the given data source
  ngOnInit(){
    this._service.getClubs()
      .subscribe(data =>{
        this.clubs = new MatTableDataSource(data);
        //sorting by values other than points
        this.clubs.sort = this.sort;
      });
  }
  @ViewChild(MatSort) sort: MatSort;
}
/*
References
https://material.angular.io/components/table/examples
 */
