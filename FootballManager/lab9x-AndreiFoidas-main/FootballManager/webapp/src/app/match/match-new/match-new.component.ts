import { Component, OnInit } from '@angular/core';
import {Match} from "../shared/match.model";
import {MatchService} from "../shared/match.service";
import {Location} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-match-new',
  templateUrl: './match-new.component.html',
  styleUrls: ['./match-new.component.css']
})
export class MatchNewComponent implements OnInit {
  submitted=false;
  modelMatch=new Match(1,1,1,'01-01-2000','stadiumName', 1, 1);

  constructor(private matchService: MatchService,
              private router: Router) { }

  onSubmit() {
    this.submitted = true;
  }

  ngOnInit(): void {
  }

  saveMatch() {
    const match: Match = <Match>{clubID1: this.modelMatch.clubID1, clubID2: this.modelMatch.clubID2,
      date: this.modelMatch.date, stadiumName: this.modelMatch.stadiumName, competitionID: this.modelMatch.competitionID,
      attendance: this.modelMatch.attendance};
    this.matchService.saveMatch(this.modelMatch)
      .subscribe(match => console.log("saved match: ", match));
    window.location.href = '/match'; //workaround
    //this.router.navigate(['/match']);
  }

}
