import { Component, OnInit } from '@angular/core';
import {ControlPanelService} from "../control-panel.service";

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.css']
})
export class PopUpComponent implements OnInit {
  message: string;
  constructor(private _service: ControlPanelService) {
  }

//
  ngOnInit(){
    console.log(this.message);
    this._service.getMessage().subscribe(data => this.message = data);
  }
}
/*
References
https://material.angular.io/components/dialog/examples
 */
