import {Club} from "../../club/shared/club.model";
import {Competition} from "../../competition/shared/competition.model";

export class Match {

   id: number | undefined;
   club1: Club | undefined;
   club2: Club | undefined;
   date: string | undefined;
   stadiumName: string | undefined;
   competition: Competition | undefined;
   attendance: number | undefined;
}
