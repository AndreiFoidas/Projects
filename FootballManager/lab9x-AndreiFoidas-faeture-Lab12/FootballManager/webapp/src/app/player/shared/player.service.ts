import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Player} from "./player.model";


@Injectable()
export class PlayerService{
  private playerUrl='http://localhost:8080/footballmanager/players';

  constructor(private httpClient: HttpClient) {
  }

  getPlayers() {
    return this.httpClient
      .get<Array<Player>>(this.playerUrl);
  }

  getPlayersFilteredByMainPosition(term: string) {
    return this.httpClient
      .get<Array<Player>>(`${this.playerUrl}/?mainPosition=${term}`);
  }
}
