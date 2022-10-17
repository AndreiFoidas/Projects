import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "./competition.model";


@Injectable()
export class CompetitionService{
  private competitionUrl='http://localhost:8080/footballmanager/competitions';

  constructor(private httpClient: HttpClient) {
  }

  getCompetitions(): Observable<Competition[]>{
    return this.httpClient.get<Array<Competition>>(this.competitionUrl);
  }

  update(competition: Competition | undefined): Observable<Competition> {
    console.warn(competition);
    return this.httpClient
      .post<Competition>(this.competitionUrl, competition);
  }

  getCompetitionsFilteredByName(term: string) {
    return this.httpClient
      .get<Array<Competition>>(`${this.competitionUrl}/?name=${term}`);
  }

  getCompetitionsReportBiggestPrizePool() {
    return this.httpClient
      .get<Array<Competition>>(`${this.competitionUrl}/?biggestPrizePool=true`);
  }
}
