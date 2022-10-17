import {Component, Input, OnInit} from '@angular/core';
import {MatchPlayer} from "../shared/matchPlayer.model";
import {MatchService} from "../../match/shared/match.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatchPlayerService} from "../shared/matchPlayer.service";
import {PlayerService} from "../../player/shared/player.service";
import {Match} from "../../match/shared/match.model";
import {Player} from "../../player/shared/player.model";
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-match-player-detail',
  templateUrl: './match-player-detail.component.html',
  styleUrls: ['./match-player-detail.component.css']
})
export class MatchPlayerDetailComponent implements OnInit {

  @Input() matchPlayer?: MatchPlayer;

  matchPlayerForm = this.formBuilder.group({
    goalsScored: ['', [Validators.required, Validators.min(0)]],
    minutesPlayed: ['', [Validators.required, Validators.min(0), Validators.max(120)]]
  });

  get matchPlayerFormControls(){
    return this.matchPlayerForm.controls;
  }

  constructor(private  matchPlayerService: MatchPlayerService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getMatchPlayer();
  }

  private convertToMatchPlayersArray(matchPlayers: any){
    return matchPlayers["mprDtoSet"]
  }
  getMatchPlayer(){
    console.log("getMatchPlayer started ---");
    const id1 = Number(this.route.snapshot.paramMap.get('id1'));
    const id2 = Number(this.route.snapshot.paramMap.get('id2'));
    this.matchPlayerService.getMatchPlayers()
      .subscribe( matchPlayers => {
          matchPlayers = this.convertToMatchPlayersArray(matchPlayers)
          this.matchPlayer = matchPlayers.find(matchPlayer => matchPlayer.match.id === id1 && matchPlayer.player.id === id2)

          this.matchPlayerForm.setValue({
            goalsScored: this.matchPlayer?.goalsScored,
            minutesPlayed: this.matchPlayer?.minutesPlayed
          });
        },
        error => console.log(error)
      );
    console.log("getMatchPlayer returned -- ", this.matchPlayer);
  }

  goBack(): void {
    this.router.navigate(['/matchPlayer']);
  }

  save() {
    // @ts-ignore
    this.matchPlayer?.goalsScored=this.matchPlayerForm.value.goalsScored;
    // @ts-ignore
    this.matchPlayer?.minutesPlayed=this.matchPlayerForm.value.minutesPlayed;
    this.matchPlayerService.updateMatchPlayer(this.matchPlayer)
      .subscribe(_ => this.goBack());
  }

  delete() {
    this.matchPlayerService.deleteMatchPlayer(this.matchPlayer)
      .subscribe(_ => this.goBack());
  }
}
