import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';

import {LeagueTableService} from './league-table.service';

import { ControlPanelComponent } from './control-panel/control-panel.component';
import {MatchTableService} from './match-table.service';
import {HttpClientModule} from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { PopUpComponent } from './pop-up/pop-up.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ScrollingModule} from "@angular/cdk/scrolling";



@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    ControlPanelComponent,
    PopUpComponent
  ],
  entryComponents: [
    PopUpComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FlexLayoutModule,
        MatTableModule,
        MatSortModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        ScrollingModule
    ],
  providers: [
    LeagueTableService,
    MatchTableService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
