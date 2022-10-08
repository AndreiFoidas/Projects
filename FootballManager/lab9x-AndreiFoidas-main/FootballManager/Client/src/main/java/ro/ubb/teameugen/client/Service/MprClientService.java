package ro.ubb.teameugen.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.common.Converter.MatchPlayerRelationConverter;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerRelationDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerRelationsDto;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MprClientService {
    private final Logger logger = LoggerFactory.getLogger(MprClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MatchPlayerRelationConverter mprConverter;

    public MatchPlayerRelation getMprById(int id) {
        logger.trace("GetMprById entered -- id = {}", id);
        var mprDto=restTemplate.getForObject(BASE_URL + "/mprs/" + id, MatchPlayerRelationDto.class);

        if(mprDto == null){
            throw new EntityNotFoundException();
        }

        return this.mprConverter.convertDtoToModel(mprDto);
    }

    public Iterable<MatchPlayerRelation> getMprs() {
        logger.trace("GetMprs started --- ");
        var mprsDto=restTemplate.getForObject(BASE_URL + "/mprs", MatchPlayerRelationsDto.class);

        if(mprsDto == null){
            return new ArrayList<>();
        }

        return mprsDto.getMprDtoSet().stream().
                map(mprDto->this.mprConverter.convertDtoToModel(mprDto))
                .collect(Collectors.toList());
    }

    public void addMpr(MatchPlayerRelation matchPlayerRelation) {
        logger.trace("AddMpr started --- ");
        restTemplate.put(BASE_URL + "/mprs", this.mprConverter.convertModelToDto(matchPlayerRelation));
        logger.trace("AddMpr returned --- ");
    }

    public void deleteMpr(int id) {
        logger.trace("DeleteMpr started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/mprs/" + id);
        logger.trace("DeleteMpr returned ---");
    }

    public void updateMpr(MatchPlayerRelation matchPlayerRelation) {
        logger.trace("UpdateMpr started -- with {}", matchPlayerRelation);
        restTemplate.postForObject(BASE_URL + "/mprs", this.mprConverter.convertModelToDto(matchPlayerRelation), MatchPlayerRelationDto.class);
        logger.trace("UpdateMpr returned ---");
    }

    public List<MatchPlayerRelation> filterMprByGoals(int goals) {
        logger.trace("FilterMprByGoals started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("goalsScored", Integer.toString(goals));

        var mprsDto=restTemplate.getForObject(BASE_URL +"/mprs?goalsScored={goalsScored}", MatchPlayerRelationsDto.class, parameters);

        if(mprsDto == null){
            return new ArrayList<>();
        }

        logger.trace("FilterMprByGoals returned ---");
        return mprsDto.getMprDtoSet().stream()
                .map(mprDto->this.mprConverter.convertDtoToModel(mprDto))
                .collect(Collectors.toList());
    }

    public List<MatchPlayerRelation> getMprWithMostGoalsScored() {
        logger.trace("GetMprWithMostGoalsScored started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("mostGoalsScored", "true");

        var mprsDto=restTemplate.getForObject(BASE_URL + "/mprs?mostGoalsScored={mostGoalsScored}", MatchPlayerRelationsDto.class, parameters);

        if(mprsDto == null){
            return new ArrayList<>();
        }

        logger.trace("GetMprWithMostGoalsScored returned ---");
        return mprsDto.getMprDtoSet().stream()
                .map(mprDto->this.mprConverter.convertDtoToModel(mprDto))
                .collect(Collectors.toList());
    }
}
