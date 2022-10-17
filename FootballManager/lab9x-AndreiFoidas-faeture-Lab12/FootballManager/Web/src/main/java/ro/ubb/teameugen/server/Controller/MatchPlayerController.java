package ro.ubb.teameugen.server.Controller;

//import ro.ubb.teameugen.common.Converter.MatchPlayerRelationConverter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.teameugen.common.Converter.MatchPlayerConverter;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayersDto;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchPlayerService;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class MatchPlayerController {
    private final Logger logger = LoggerFactory.getLogger(MatchPlayerController.class);

    @Autowired
    private IMatchPlayerService matchPlayerService;

    @Autowired
    private MatchPlayerConverter matchPlayerConverter;

    @RequestMapping(value="/matchPlayers/{matchId}", method = RequestMethod.GET)
    public MatchPlayersDto getAllMatchPlayersForAMatch(@PathVariable int matchId){
        logger.trace("[GET] /matchPlayers/{} - getAllMatchPlayersForAMatch entered", matchId);
        var matchPlayers=new HashSet<MatchPlayerDto>();

        this.matchPlayerService.getAllMatchPlayersForAMatch(matchId)
                .forEach(matchPlayer->matchPlayers.add(matchPlayerConverter.convertModelToDto(matchPlayer)));

        logger.trace("getAllMatchPlayersForAMatch returned - {}", matchPlayers);
        return new MatchPlayersDto((matchPlayers));
    }

    @RequestMapping(value="/matchPlayers", method= RequestMethod.GET)
    public MatchPlayersDto getAllMatchPlayers(@RequestParam(required = false) Boolean mostGoals){
        logger.trace("[GET] /matchPlayers - getAllMatchPlayers entered with parameters mostGoals = {}", mostGoals);

        var matchPlayers=new CopyOnWriteArraySet<MatchPlayerDto>();

        if(mostGoals != null){
            logger.trace("Parameters for #getMostGoalsMatchPlayers()");
            this.matchPlayerService.getMostGoalsMatchPlayer()
                    .forEach(matchPlayer -> matchPlayers.add(matchPlayerConverter.convertModelToDto(matchPlayer)));
        }
        else {
            logger.trace("Parameters for #getAllMatchPlayers()");
            this.matchPlayerService.getAll()
                    .forEach(matchPlayer -> matchPlayers.add(matchPlayerConverter.convertModelToDto(matchPlayer)));
        }
        logger.trace("getAll returned - {}", matchPlayers);
        return new MatchPlayersDto((matchPlayers));
    }

    @RequestMapping(value="/matchPlayers", method = RequestMethod.PUT)
    public void addMatchPlayer(@RequestBody MatchPlayerDto matchPlayerDto){
        logger.trace("[PUT] /matchPlayers - addMatchPlayer(matchPlayerDto = {}) entered",
                matchPlayerDto);
        logger.trace("\n\n\n\n\n\n\n\n1\n\n\n\n\n\n\n\n");

        matchPlayerService.addMatchPlayer(matchPlayerDto.getMatch().getId(), matchPlayerDto.getPlayer().getId(),
                matchPlayerDto.getGoalsScored(), matchPlayerDto.getMinutesPlayed());

        logger.trace("addMatchPlayer returned ---");
    }

    @RequestMapping(value="/matchPlayers", method = RequestMethod.POST)
    public void updateMatchPlayer(@RequestBody MatchPlayerDto matchPlayerDto){
        logger.trace("[POST] /matchPlayers - updateMatchPlayer(matchPlayerDto = {}) entered",
                matchPlayerDto);
        matchPlayerService.updateMatchPlayer(matchPlayerDto.getMatch().getId(), matchPlayerDto.getPlayer().getId(), matchPlayerDto.getGoalsScored(), matchPlayerDto.getMinutesPlayed());

        logger.trace("updateMatchPlayer returned ---");
    }

    @RequestMapping(value = "/matchPlayers/{mid}/{pid}", method = RequestMethod.DELETE)
    public void deleteMatchPlayer(@PathVariable int mid, @PathVariable int pid){
        logger.trace("[DELETE] /matchPlayers/{}/{} - deleteMatchPlayer() entered", mid, pid);

        matchPlayerService.deleteMatchPlayer(mid, pid);

        logger.trace("deleteMatchPlayer returned ---");
    }

    /*@RequestMapping(value="/matchPlayer", method= RequestMethod.GET)
    public MatchPlayersDto getAllMprs(@RequestParam(required = false) Integer goalsScored,
                                      @RequestParam(required = false) Boolean mostGoalsScored){
        logger.trace("[GET] /mprs - getAllMprs entered with parameters goalsScored = {}, mostGoalsScored = {}", goalsScored, mostGoalsScored);
        var mprs=new HashSet<MatchPlayerDto>();

        if (goalsScored != null){
            logger.trace("Parameters for #filterMprByGoals(goalsScored)");
            this.matchPlayerService.filterMprByGoals(goalsScored).forEach(mpr->mprs.add(matchPlayerConverter.convertModelToDto(mpr)));
        } else if (mostGoalsScored != null){
            logger.trace("Parameters for #getMprWithMostGoalsScored()");
            this.matchPlayerService.getMprWithMostGoalsScored().forEach(mpr->mprs.add(matchPlayerConverter.convertModelToDto(mpr)));
        } else {
            logger.trace("Parameters for #getMprs()");
            this.matchPlayerService.getMprs().forEach(mpr->mprs.add(matchPlayerConverter.convertModelToDto(mpr)));
        }

        logger.trace("getAllMprs returned - {}", mprs);
        return new MatchPlayersDto((mprs));
    }

    @RequestMapping(value="/matchPlayer/{id}", method=RequestMethod.GET)
    public MatchPlayerDto getMpr(@PathVariable int id){
        logger.trace("[GET] /mprs/{id} - getMpr(id = {}) entered", id);

        try{
            var mpr=this.matchPlayerService.getMprById(id);
            logger.trace("getMpr returned - {}", mpr);
            return this.matchPlayerConverter.convertModelToDto(mpr);
        } catch (EntityNotFoundException e) {
            logger.trace("getMpr threw ResponseStatusException - MatchPlayerRelation not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested matchPlayerRelation was not found.");
        }
    }

    @RequestMapping(value="/matchPlayer", method=RequestMethod.PUT)
    public void addMpr(@RequestBody MatchPlayerDto mprDto){
        logger.trace("[PUT] /mprs - addMpr(matchPlayerRelation = {}) entered", mprDto);
        this.matchPlayerService.addMpr(this.matchPlayerConverter.convertDtoToModel(mprDto));
        logger.trace("addMpr returned ---");
    }

    @RequestMapping(value="/matchPlayer", method=RequestMethod.POST)
    public void updateMpr(@RequestBody MatchPlayerDto mprDto){
        logger.trace("[POST] /mprs - updateMpr(matchPlayerRelation = {}) entered", mprDto);
        this.matchPlayerService.updateMpr(this.matchPlayerConverter.convertDtoToModel(mprDto));
        logger.trace("updateMpr returned ---");
    }

    @RequestMapping(value = "/matchPlayer/{id}", method = RequestMethod.DELETE)
    public void deleteMpr(@PathVariable int id){
        logger.trace("[DELETE] /mprs/{} - deleteMpr() entered", id);
        this.matchPlayerService.deleteMpr(id);
        logger.trace("deleteMpr returned ---");
    }*/
}
