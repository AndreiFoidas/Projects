import {Component, Input, OnInit} from '@angular/core';
import {Match} from "../shared/match.model";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MatchService} from "../shared/match.service";
import {ClubService} from "../../club/shared/club.service";
import {CompetitionService} from "../../competition/shared/competition.service";
import {Club} from "../../club/shared/club.model";
import {Competition} from "../../competition/shared/competition.model";

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.css']
})
export class MatchDetailComponent implements OnInit {

  @Input() match?: Match;

  clubs: Club[] | undefined;
  competitions: Competition[] | undefined;

  constructor(private  matchService: MatchService,
              private clubService: ClubService,
              private competitionService: CompetitionService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getClubs();
    this.getCompetitions();
    this.getMatch();
  }

  convertToMatchesArray(matches: any){
    return matches['matchDtoSet'];
  }

  getMatch(): void{
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.matchService.getMatches()
      .subscribe(matches => {
        matches=this.convertToMatchesArray(matches)
        this.match = matches.find(match => match.id === id)
        // @ts-ignore
        this.match.club1 = matches.find(match => match.id === id).club1
      });
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

  saveChanges(): void {
    const club1=this.clubs?.find(c => c.id == this.match?.club1);
    const club2=this.clubs?.find(c => c.id == this.match?.club2);
    const competition=this.competitions?.find(c => c.id == this.match?.competition);
    const match: Match = <Match>{id: this.match?.id, club1: club1, club2: club2, date: this.match?.date,
      stadiumName: this.match?.stadiumName, competition: competition, attendance: this.match?.attendance};

    console.log(match);
    this.matchService.update(match)
      .subscribe(_ => this.goBack());
  }

  deleteMatch(): void {
    this.matchService.delete(this.match)
      .subscribe(_ => this.goBack());
  }

  goBack(): void {
    this.router.navigate(['/match']);
  }

}
