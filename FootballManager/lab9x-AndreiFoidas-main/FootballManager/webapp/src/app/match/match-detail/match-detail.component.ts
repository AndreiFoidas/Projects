import {Component, Input, OnInit} from '@angular/core';
import {Match} from "../shared/match.model";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MatchService} from "../shared/match.service";
import {switchMap} from "rxjs/operators";
import {Location} from '@angular/common';

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.css']
})
export class MatchDetailComponent implements OnInit {

  @Input() match?: Match;

  constructor(private  matchService: MatchService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
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
      });
  }

  saveChanges(): void {
    this.matchService.update(this.match)
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
