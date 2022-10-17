import { Component, OnInit } from '@angular/core';
import {Match} from "../shared/match.model";
import {MatchService} from "../shared/match.service";
import {ClubService} from "../../club/shared/club.service";
import {Club} from "../../club/shared/club.model";
import {Competition} from "../../competition/shared/competition.model";
import {CompetitionService} from "../../competition/shared/competition.service";

@Component({
  selector: 'app-match-new',
  templateUrl: './match-new.component.html',
  styleUrls: ['./match-new.component.css']
})
export class MatchNewComponent implements OnInit {
  submitted=false;
  modelMatch=new Match();

  clubs: Club[] | undefined;
  competitions: Competition[] | undefined;

  constructor(private matchService: MatchService,
              private clubService: ClubService,
              private competitionService: CompetitionService) { }

  onSubmit() {
    this.submitted = true;
  }

  ngOnInit(): void {
    this.getClubs();
    this.getCompetitions();
  }

  convertToClubsArray(clubs: any){
    return clubs['clubDtoSet'];
  }
  getClubs() {
    console.log("getClubs started ---");
    this.clubService.getClubs()
      .subscribe(
        clubs => this.clubs=this.convertToClubsArray(clubs),
        error => console.log(error),
      );
    console.log("getClubs returned -- ", this.clubs);
  }

  convertToCompetitionsArray(competitions: any){
    return competitions['competitionDtoSet'];
  }

  getCompetitions(){
    console.log("getCompetitions started ---");
    this.competitionService.getCompetitions()
      .subscribe(
        competitions => this.competitions=this.convertToCompetitionsArray(competitions),
        error => console.log(error),
      );
    console.log("getCompetitions returned -- ", this.competitions);
  }

  saveMatch() {
    const club1=this.clubs?.find(c => c.id == this.modelMatch.club1);
    const club2=this.clubs?.find(c => c.id == this.modelMatch.club2);
    const competition=this.competitions?.find(c => c.id == this.modelMatch.competition);
    const match: Match = <Match>{club1: club1, club2: club2, date: this.modelMatch.date,
      stadiumName: this.modelMatch.stadiumName, competition: competition, attendance: this.modelMatch.attendance};
    console.log(match);
    this.matchService.saveMatch(match)
      .subscribe(match => console.log("saved match: ", match));
    window.location.href = '/match';
  }

}
