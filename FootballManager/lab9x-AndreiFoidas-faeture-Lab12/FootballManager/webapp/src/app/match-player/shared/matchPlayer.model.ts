import {Match} from "../../match/shared/match.model";
import {Player} from "../../player/shared/player.model";

export class MatchPlayer {
  match!: Match;
  player!: Player;
  goalsScored: number | undefined;
  minutesPlayed: number | undefined;
}
