import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {PopUpComponent} from '../pop-up/pop-up.component';


@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent  {
  //
  constructor(public dialog: MatDialog) {}

  //displays the message for adding a match and updates the page
  openDialog(): void{
    this.dialog.open(PopUpComponent).afterClosed().subscribe(() => location.reload());
  }
}
