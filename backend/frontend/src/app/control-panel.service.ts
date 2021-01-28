import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ControlPanelService {
  // tslint:disable-next-line:variable-name
  private _url: string = '/api/random ';

  constructor(private http: HttpClient) {
  }
  getMessage():Observable<string>{
    return this.http.get<string>(this._url);
  }
}
