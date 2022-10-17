import { Component, OnInit } from '@angular/core';
import {Match} from "../../match/shared/match.model";
import {Player} from "../../player/shared/player.model";
import {MatchPlayerService} from "../shared/matchPlayer.service";
import {PlayerService} from "../../player/shared/player.service";
import {MatchService} from "../../match/shared/match.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {MatchPlayer} from "../shared/matchPlayer.model";

@Component({
  selector: 'app-match-player-new',
  templateUrl: './match-player-new.component.html',
  styleUrls: ['./match-player-new.component.css']
})
export class MatchPlayerNewComponent implements OnInit {

  matches: Match[] | undefined;
  players: Player[] | undefined;

  modelMatchPlayer=new MatchPlayer();

  constructor(private  matchPlayerService: MatchPlayerService,
              private playerService: PlayerService,
              private matchService: MatchService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getPlayers();
    this.getMatches();
  }

  private convertToPlayersArray(players: any) {
    return players["playerDtoSet"];
  }
  getPlayers() {
    console.log("getPlayers started ---");
    this.playerService.getPlayers()
      .subscribe(
        players => this.players=this.convertToPlayersArray(players),
        error => console.log(error),
      );
    console.log("getPlayers returned -- ", this.players);
  }
  convertToMatchesArray(matches: any){
    return matches['matchDtoSet'];
  }
  getMatches(){
    console.log("getMatches started ---");
    console.log(this.matchService.getMatches());
    this.matchService.getMatches()
      .subscribe(
        matches => this.matches=this.convertToMatchesArray(matches),
        error => console.log(error),
      );
    console.log("getMatches returned -- ", this.matches);
  }

  goBack(): void {
    this.router.navigate(['/matchPlayer']);
  }

  addMatchPlayer() {
    // @ts-ignore
    const match = this.matches?.find(m => m.id == this.modelMatchPlayer?.match);
    // @ts-ignore
    const player=this.players?.find(p => p.id == this.modelMatchPlayer.player);
    const matchPlayer: MatchPlayer = <MatchPlayer>{match: match, player: player,
      goalsScored: this.modelMatchPlayer?.goalsScored, minutesPlayed: this.modelMatchPlayer?.minutesPlayed};
    console.log(matchPlayer);
    this.matchPlayerService.saveModelMatch(matchPlayer)
      .subscribe(matchPlayer => {console.log("saved matchPlayer:", matchPlayer); this.goBack();})
  }
}
