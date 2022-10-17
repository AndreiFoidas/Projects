import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MatchPlayer} from "./matchPlayer.model";

@Injectable()
export class MatchPlayerService{
  private matchPlayerUrl='http://localhost:8080/footballmanager/matchPlayers';

  constructor(private httpClient: HttpClient) {
  }

  getMatchPlayers(): Observable<MatchPlayer[]> {
    return this.httpClient.get<Array<MatchPlayer>>(this.matchPlayerUrl);
  }

  updateMatchPlayer(matchPlayer: MatchPlayer | undefined): Observable<MatchPlayer> {
    console.log(matchPlayer);
    return this.httpClient.post<MatchPlayer>(this.matchPlayerUrl, matchPlayer);
  }

  deleteMatchPlayer(matchPlayer: MatchPlayer | undefined): Observable<MatchPlayer> {
    // @ts-ignore
    const url=`${this.matchPlayerUrl}/${matchPlayer.match.id}/${matchPlayer.player.id}`;
    console.log(url);
    return this.httpClient.delete<MatchPlayer>(url);
  }

  saveModelMatch(matchPlayer: MatchPlayer): Observable<MatchPlayer> {
    console.log(matchPlayer);
    return this.httpClient.put<MatchPlayer>(this.matchPlayerUrl, matchPlayer);
  }

  getMostGoals() {
    return this.httpClient.
      get<Array<MatchPlayer>>(`${this.matchPlayerUrl}/?mostGoals=true`);
  }
}
