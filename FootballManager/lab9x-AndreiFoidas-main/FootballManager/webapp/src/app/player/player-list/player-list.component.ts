import { Component, OnInit } from '@angular/core';
import {Player} from "../shared/player.model";
import {PlayerService} from "../shared/player.service";

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {
  players: Player[] | undefined;

  positions = ['', 'Midfielder', 'Striker', 'Goalkeeper', 'Defender'];

  constructor(private playerService: PlayerService) { }

  ngOnInit(): void {
    this.getPlayers();
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

  filterByMainPosition(term: string) {
    this.playerService.getPlayers()
      .subscribe(
        players => {
          players=this.convertToPlayersArray(players)
          this.players=players.filter(player => player.mainPosition === term)},
        error => console.log(error),
      );
  }

  reportYoungestPlayer() {
    this.playerService.getPlayers()
      .subscribe(
        players => {
          players=this.convertToPlayersArray(players)
          let bdays = players.map(player => player.birthday)
          let minBday=bdays.sort().reverse().pop()
          this.players = players.filter(player => player.birthday==minBday)},
        error => console.log(error),
      );
  }

  sortByName(){
    this.playerService.getPlayers()
      .subscribe(
        players => {
          players=this.convertToPlayersArray(players)
          // @ts-ignore
          this.players=players.sort((p1,p2) => p1.playerName?.localeCompare(p2.playerName) | undefined)},
          error => console.log(error),
      )
  }
}
