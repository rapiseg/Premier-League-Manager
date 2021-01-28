import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Match} from './match-table/match-table.component';
import {catchError} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class MatchTableService {
  // tslint:disable-next-line:variable-name
  private _url: string = "/api/match ";
  constructor(private http: HttpClient) {
  }

  getMatches(): Observable<Match[]>{
    return this.http.get<Match[]>(this._url)
      .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpErrorResponse){
    return Observable.throw(error.message || "Server Error")
  }
}
