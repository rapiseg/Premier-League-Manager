import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FootballClub} from './league-table/league-table.component';
import {catchError} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class LeagueTableService {
  private _url: string = '/api/league ';
  constructor(private http: HttpClient) {
  }


  getClubs(): Observable<FootballClub[]>{
    return this.http.get<FootballClub[]>(this._url)
      .pipe(catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse){
    return Observable.throw(error.message || "Server Error")
  }
}
