import { Component, OnInit } from '@angular/core';
import {MatchPlayer} from "../shared/matchPlayer.model";
import {MatchPlayerService} from "../shared/matchPlayer.service";

import {Router} from "@angular/router";

@Component({
  selector: 'app-match-player-list',
  templateUrl: './match-player-list.component.html',
  styleUrls: ['./match-player-list.component.css']
})
export class MatchPlayerListComponent implements OnInit {
  matchPlayers: MatchPlayer[] | undefined;

  selectedMatchPlayer?: MatchPlayer;

  constructor(private matchPlayerService: MatchPlayerService,
              private router: Router) { }

  ngOnInit(): void {
    this.getMatchPlayers();
  }

  private convertToMatchPlayersArray(matchPlayers: any){
    return matchPlayers["mprDtoSet"]
  }
  getMatchPlayers(){
    console.log("getMatchPlayers started ---");
    this.matchPlayerService.getMatchPlayers()
      .subscribe(
        matchPlayers => this.matchPlayers=this.convertToMatchPlayersArray(matchPlayers),
        error => console.log(error)
      );
    console.log("getMatchPlayers returned -- ", this.matchPlayers);
  }

  reportMostGoals() {
    console.log("getMatchPlayers started ---");
    this.matchPlayerService.getMostGoals()
      .subscribe(
        matchPlayers => this.matchPlayers=this.convertToMatchPlayersArray(matchPlayers),
        error => console.log(error)
      );
    console.log("getMatchPlayers returned -- ", this.matchPlayers);
  }

  onSelect(matchPlayer: MatchPlayer): void {
    this.selectedMatchPlayer=matchPlayer;
  }

  gotoDetail(): void{
    this.router.navigate(['/matchPlayer/details', this.selectedMatchPlayer?.match.id, this.selectedMatchPlayer?.player.id]);
  }

  goToNew() {
    this.router.navigate(['/matchPlayer/new']);
  }
}
