import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Club} from "./club.model";


@Injectable()
export class ClubService{
  private clubUrl='http://localhost:8080/footballmanager/clubs';

  constructor(private httpClient: HttpClient){}

  getClubs() {
    return this.httpClient
      .get<Array<Club>>(this.clubUrl);
  }

  getClubsFilteredByCountry(term: string){
    return this.httpClient
      .get<Array<Club>>(`${this.clubUrl}/?country=${term}`);
  }

  getClubsFilteredByOwner(term: string){
    return this.httpClient
      .get<Array<Club>>(`${this.clubUrl}/?owner=${term}`);
  }

  getClubsReportMaxBudget(){
    return this.httpClient
      .get<Array<Club>>(`${this.clubUrl}/?maxBudget=true`);
  }

  getClubsSortedByName(){
    return this.httpClient
      .get<Array<Club>>(`${this.clubUrl}/?sortByName=true`);
  }
}
