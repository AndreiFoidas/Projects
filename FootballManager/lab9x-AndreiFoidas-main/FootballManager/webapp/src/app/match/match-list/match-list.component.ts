import { Component, OnInit } from '@angular/core';
import {Match} from "../shared/match.model";
import {MatchService} from "../shared/match.service";
import {Router} from "@angular/router";
import {element} from "protractor";

@Component({
  selector: 'app-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.css']
})
export class MatchListComponent implements OnInit {
  matches: Match[] | undefined;
  selectedMatch?: Match;
  save:boolean=false;

  constructor(private matchService: MatchService,
              private router: Router) { }

  ngOnInit(): void {
    this.getMatches();
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

  onSelect(match: Match): void{
    this.selectedMatch=match;
  }

  gotoDetail(): void {
    this.router.navigate(['/match/details', this.selectedMatch?.id]);
  }

  displaySave(): void{
    this.save=true;
  }

  closeSave() {
    this.save=false;
  }

  deleteMatch() {
    let id=this.selectedMatch?.id;
    console.log(id);
    this.matchService.delete(this.selectedMatch)
      .subscribe(_ => this.matches=this.matches?.filter(m => m.id !== id),
        this.selectedMatch=undefined);
    console.log(this.matches);

  }
}
