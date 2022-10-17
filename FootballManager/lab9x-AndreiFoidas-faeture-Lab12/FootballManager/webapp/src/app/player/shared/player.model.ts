import {Club} from "../../club/shared/club.model";

export class Player {
  id: number | undefined;
  playerName: string | undefined;
  birthday: string | undefined;
  wage: number | undefined;
  mainPosition: string | undefined;

  club!: Club;

  street: string | undefined;
  city: string | undefined;
}
