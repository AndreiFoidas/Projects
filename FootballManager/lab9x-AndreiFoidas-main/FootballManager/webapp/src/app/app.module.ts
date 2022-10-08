import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatchComponent } from './match/match.component';
import { MatchListComponent } from './match/match-list/match-list.component';
import {MatchService} from "./match/shared/match.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MatchDetailComponent } from './match/match-detail/match-detail.component';
import { MatchNewComponent } from './match/match-new/match-new.component';
import {ClubComponent} from "./club/club.component";
import { ClubListComponent } from './club/club-list/club-list.component';
import {ClubService} from "./club/shared/club.service";
import { PlayerComponent } from './player/player.component';
import {PlayerService} from "./player/shared/player.service";
import { PlayerListComponent } from './player/player-list/player-list.component';
import { CompetitionComponent } from './competition/competition.component';
import {CompetitionService} from "./competition/shared/competition.service";
import { CompetitionListComponent } from './competition/competition-list/competition-list.component';
import { CompetitionDetailComponent } from './competition/competition-detail/competition-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    MatchComponent,
    MatchListComponent,
    MatchDetailComponent,
    MatchNewComponent,
    ClubComponent,
    ClubListComponent,
    PlayerComponent,
    PlayerListComponent,
    CompetitionComponent,
    CompetitionListComponent,
    CompetitionDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [MatchService, ClubService, PlayerService, CompetitionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
