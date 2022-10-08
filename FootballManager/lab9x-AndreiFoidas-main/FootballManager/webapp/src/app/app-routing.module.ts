import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MatchComponent} from "./match/match.component";
import {MatchDetailComponent} from "./match/match-detail/match-detail.component";
import {MatchNewComponent} from "./match/match-new/match-new.component";
import {ClubComponent} from "./club/club.component";
import {PlayerComponent} from "./player/player.component";
import {CompetitionComponent} from "./competition/competition.component";
import {CompetitionDetailComponent} from "./competition/competition-detail/competition-detail.component";

const routes: Routes = [
  {path: 'match', component: MatchComponent},
  {path: 'match/details/:id', component: MatchDetailComponent},
  {path: 'match-new', component: MatchNewComponent},

  {path: 'club', component: ClubComponent},

  {path: 'player', component: PlayerComponent},

  {path: 'competition', component: CompetitionComponent},
  {path: 'competition/details/:id', component: CompetitionDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
