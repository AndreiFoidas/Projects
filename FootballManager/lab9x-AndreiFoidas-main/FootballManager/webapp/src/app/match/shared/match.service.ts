import {Match} from "./match.model"
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class MatchService{
  private matchUrl='http://localhost:8080/footballmanager/matches';

  constructor(private httpClient: HttpClient) {
  }

  getMatches(): Observable<Match[]>{
    return this.httpClient.get<Array<Match>>(this.matchUrl);
  }

  saveMatch(match: Match): Observable<Match> {
    return this.httpClient
      .put<Match>(this.matchUrl, match);
  }

  update(match: Match | undefined): Observable<Match>{
    return this.httpClient
      .post<Match>(this.matchUrl, match);
  }

  delete(match: Match | undefined): Observable<Match> {
    // @ts-ignore
    const url = `${this.matchUrl}/${match.id}`;
    return this.httpClient
      .delete<Match>(url);
  }
}
