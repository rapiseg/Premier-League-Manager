import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LeagueTableComponent} from './league-table/league-table.component';
import {MatchTableComponent} from './match-table/match-table.component';

const routes: Routes = [
  {path: '', component: LeagueTableComponent},
  {path: 'matchTable', component: MatchTableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LeagueTableComponent, MatchTableComponent]
