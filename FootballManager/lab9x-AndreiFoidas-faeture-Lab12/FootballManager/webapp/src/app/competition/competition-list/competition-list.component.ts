import { Component, OnInit } from '@angular/core';
import {Competition} from "../shared/competition.model";
import {CompetitionService} from "../shared/competition.service";
import {Router} from "@angular/router";
import {Match} from "../../match/shared/match.model";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {
  competitions: Competition[] | undefined;
  selectedCompetition?: Competition;

  constructor(private competitionService: CompetitionService,
              private router: Router) { }

  ngOnInit(): void {
    this.getCompetitions();
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

  onSelect(competition: Competition): void{
    this.selectedCompetition=competition;
  }

  gotoDetail(): void {
    this.router.navigate(['/competition/details', this.selectedCompetition?.id]);
  }

  filterByName(term: string) {
    this.competitionService.getCompetitionsFilteredByName(term)
      .subscribe(
        competitions => this.competitions=this.convertToCompetitionsArray(competitions),
        error => console.log(error)
      );
  }

  reportBiggestPrizePool() {
    this.competitionService.getCompetitionsReportBiggestPrizePool()
      .subscribe(
        competitions => this.competitions=this.convertToCompetitionsArray(competitions),
        error => console.log(error)
      );
  }
}
