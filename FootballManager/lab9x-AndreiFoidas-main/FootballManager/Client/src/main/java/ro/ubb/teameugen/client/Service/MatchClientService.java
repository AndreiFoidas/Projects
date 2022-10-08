package ro.ubb.teameugen.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.common.Converter.MatchConverter;
import ro.ubb.teameugen.common.Domain.DTOs.MatchDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchesDto;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MatchClientService {
    private final Logger logger = LoggerFactory.getLogger(MatchClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MatchConverter matchConverter;

    public Match getMatchById(int id) {
        logger.trace("GetMatchById started --- with id {}", id);
        var matchDTO = restTemplate.getForObject(BASE_URL + "/matches/" + id, MatchDto.class);

        if (matchDTO == null) {
            throw new EntityNotFoundException();
        }

        return matchConverter.convertDtoToModel(matchDTO);
    }

    public Iterable<Match> getMatches() {
        logger.trace("getMatches started --- ");
        var matchesDTO = restTemplate.getForObject(BASE_URL + "/matches", MatchesDto.class);
        if (matchesDTO == null) {
            return new ArrayList<>();
        }

        return matchesDTO.getMatchDtoSet().stream()
                .map(matchDTO -> matchConverter.convertDtoToModel(matchDTO))
                .collect(Collectors.toList());
    }

    public void addMatch(Match match) {
        logger.trace("addMatch started -- with {}", match);
        restTemplate.put(BASE_URL + "/matches", matchConverter.convertModelToDto(match));
        logger.trace("addMatch returned ---");
    }

    public void deleteMatch(int id) {
        logger.trace("deleteMatch started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/matches/" + id);
        logger.trace("deleteMatch returned ---");
    }

    public void updateMatch(Match match) {
        logger.trace("updateMatch started -- with {}", match);
        restTemplate.postForObject(BASE_URL + "/matches", matchConverter.convertModelToDto(match), MatchDto.class);
        logger.trace("updateMatch returned ---");
    }

    public List<Match> filterMatchByAttendance(int attendance) {
        logger.trace("filterMatchByAttendance started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("attendance", Integer.toString(attendance));

        var matchesDTO = restTemplate.getForObject(BASE_URL + "/matches?attendance={attendance}", MatchesDto.class, parameters);
        if (matchesDTO == null) {
            return new ArrayList<>();
        }

        logger.trace("filterMatchByAttendance returned ---");
        return matchesDTO.getMatchDtoSet().stream().map(matchDTO -> matchConverter.convertDtoToModel(matchDTO)).collect(Collectors.toList());
    }

    public List<Match> getMatchWithLowestAttendance() {
        logger.trace("getMatchWithLowestAttendance started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("lowestAttendance", "true");

        var matchesDTO = restTemplate.getForObject(BASE_URL + "/matches?lowestAttendance={lowestAttendance}", MatchesDto.class, parameters);
        if (matchesDTO == null) {
            return new ArrayList<>();
        }

        logger.trace("getMatchWithLowestAttendance returned ---");
        return matchesDTO.getMatchDtoSet().stream().map(matchDto -> matchConverter.convertDtoToModel(matchDto)).collect(Collectors.toList());
    }

    public Integer collectNumberOfMatchesPlayedInStadium(String stadiumName) {
        logger.trace("collectNumberOfMatchesPlayedInStadium started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("stadiumName", stadiumName);

        var numberOfMatches = restTemplate.getForObject(BASE_URL + "/matches/stadium?stadiumName={stadiumName}", Integer.class, parameters);
        logger.trace("collectNumberOfMatchesPlayedInStadium returned ---");
        return numberOfMatches;
    }
}
