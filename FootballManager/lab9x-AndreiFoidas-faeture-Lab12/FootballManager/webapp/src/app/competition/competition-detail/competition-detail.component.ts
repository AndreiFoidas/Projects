import {Component, Input, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CompetitionService} from "../shared/competition.service";
import {Competition} from "../shared/competition.model";
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-competition-detail',
  templateUrl: './competition-detail.component.html',
  styleUrls: ['./competition-detail.component.css']
})
export class CompetitionDetailComponent implements OnInit {

  @Input() competition?: Competition;

  competitionForm = this.formBuilder.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    prizePool: ['', [Validators.required, Validators.min(0)]],
    firstYear: ['', [Validators.required, Validators.min(1800)]]
  });

  get competitionFormControls(){
    return this.competitionForm.controls;
  }

  constructor(private  competitionService: CompetitionService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCompetition();
  }

  convertToCompetitionsArray(competitions: any){
    return competitions['competitionDtoSet'];
  }

  getCompetition(): void{
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.competitionService.getCompetitions()
      .subscribe(competitions => {
        competitions=this.convertToCompetitionsArray(competitions)
        this.competition = competitions.find(competition => competition.id === id)

        this.competitionForm.setValue({
          name: this.competition?.name,
          prizePool: this.competition?.prizePool,
          firstYear: this.competition?.firstYear
        });
      });
  }

  onSubmit() {
    // @ts-ignore
    this.competition?.firstYear=this.competitionForm.value.firstYear;
    // @ts-ignore
    this.competition?.name=this.competitionForm.value.name;
    // @ts-ignore
    this.competition?.prizePool=this.competitionForm.value.prizePool;
    this.competitionService.update(this.competition)
      .subscribe(_ => this.goBack());
  }

  goBack(): void {
    this.router.navigate(['/competition']);
  }
}
