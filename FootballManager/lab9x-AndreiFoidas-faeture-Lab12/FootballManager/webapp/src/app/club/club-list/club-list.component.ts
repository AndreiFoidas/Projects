import { Component, OnInit } from '@angular/core';
import {ClubService} from "../shared/club.service";
import {Router} from "@angular/router";
import {Club} from "../shared/club.model";

@Component({
  selector: 'app-club-list',
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css']
})
export class ClubListComponent implements OnInit {
  clubs: Club[] | undefined;


  constructor(private clubService: ClubService,
              private router: Router) { }

  ngOnInit(): void {
    this.getClubs();
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

  sortByName(){
    this.clubService.getClubsSortedByName()
      .subscribe(
        clubs => this.clubs=this.convertToClubsArray(clubs),
        error => console.log(error),
      );
  }

  filterByCountry(term: string) {
    this.clubService.getClubsFilteredByCountry(term)
      .subscribe(
        clubs => this.clubs=this.convertToClubsArray(clubs),
        error => console.log(error),
      );
  }

  filterByOwner(term: string) {
    this.clubService.getClubsFilteredByOwner(term)
      .subscribe(
        clubs => this.clubs=this.convertToClubsArray(clubs),
        error => console.log(error),
      );
  }

  reportMaxBudget() {
    this.clubService.getClubsReportMaxBudget()
      .subscribe(
        clubs => this.clubs=this.convertToClubsArray(clubs),
        error => console.log(error),
      );
  }
}
