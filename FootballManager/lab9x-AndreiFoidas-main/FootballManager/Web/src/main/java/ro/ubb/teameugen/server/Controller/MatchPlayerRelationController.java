package ro.ubb.teameugen.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.ubb.teameugen.common.Converter.MatchPlayerRelationConverter;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerRelationDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerRelationsDto;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Service.Interfaces.IMprService;

import java.util.HashSet;


@RestController
public class MatchPlayerRelationController {
    private final Logger logger = LoggerFactory.getLogger(MatchPlayerRelationController.class);

    @Autowired
    private IMprService mprService;

    @Autowired
    private MatchPlayerRelationConverter mprConverter;

    @RequestMapping(value="/mprs", method= RequestMethod.GET)
    public MatchPlayerRelationsDto getAllMprs(@RequestParam(required = false) Integer goalsScored,
                                              @RequestParam(required = false) Boolean mostGoalsScored){
        logger.trace("[GET] /mprs - getAllMprs entered with parameters goalsScored = {}, mostGoalsScored = {}", goalsScored, mostGoalsScored);
        var mprs=new HashSet<MatchPlayerRelationDto>();

        if (goalsScored != null){
            logger.trace("Parameters for #filterMprByGoals(goalsScored)");
            this.mprService.filterMprByGoals(goalsScored).forEach(mpr->mprs.add(mprConverter.convertModelToDto(mpr)));
        } else if (mostGoalsScored != null){
            logger.trace("Parameters for #getMprWithMostGoalsScored()");
            this.mprService.getMprWithMostGoalsScored().forEach(mpr->mprs.add(mprConverter.convertModelToDto(mpr)));
        } else {
            logger.trace("Parameters for #getMprs()");
            this.mprService.getMprs().forEach(mpr->mprs.add(mprConverter.convertModelToDto(mpr)));
        }

        logger.trace("getAllMprs returned - {}", mprs);
        return new MatchPlayerRelationsDto((mprs));
    }

    @RequestMapping(value="/mprs/{id}", method=RequestMethod.GET)
    public MatchPlayerRelationDto getMpr(@PathVariable int id){
        logger.trace("[GET] /mprs/{id} - getMpr(id = {}) entered", id);

        try{
            var mpr=this.mprService.getMprById(id);
            logger.trace("getMpr returned - {}", mpr);
            return this.mprConverter.convertModelToDto(mpr);
        } catch (EntityNotFoundException e) {
            logger.trace("getMpr threw ResponseStatusException - MatchPlayerRelation not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested matchPlayerRelation was not found.");
        }
    }

    @RequestMapping(value="/mprs", method=RequestMethod.PUT)
    public void addMpr(@RequestBody MatchPlayerRelationDto mprDto){
        logger.trace("[PUT] /mprs - addMpr(matchPlayerRelation = {}) entered", mprDto);
        this.mprService.addMpr(this.mprConverter.convertDtoToModel(mprDto));
        logger.trace("addMpr returned ---");
    }

    @RequestMapping(value="/mprs", method=RequestMethod.POST)
    public void updateMpr(@RequestBody MatchPlayerRelationDto mprDto){
        logger.trace("[POST] /mprs - updateMpr(matchPlayerRelation = {}) entered", mprDto);
        this.mprService.updateMpr(this.mprConverter.convertDtoToModel(mprDto));
        logger.trace("updateMpr returned ---");
    }

    @RequestMapping(value = "/mprs/{id}", method = RequestMethod.DELETE)
    public void deleteMpr(@PathVariable int id){
        logger.trace("[DELETE] /mprs/{} - deleteMpr() entered", id);
        this.mprService.deleteMpr(id);
        logger.trace("deleteMpr returned ---");
    }
}
