package ro.ubb.teameugen.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.common.Converter.CompetitionConverter;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionDto;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionsDto;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompetitionClientService {
    private final Logger logger = LoggerFactory.getLogger(CompetitionClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompetitionConverter competitionConverter;

    public Competition getCompetitionById(int id) {
        logger.trace("getCompetitionById started --- with id {}", id);
        var competitionDTO = restTemplate.getForObject(BASE_URL + "/competitions/" + id, CompetitionDto.class);
        if (competitionDTO == null) {
            throw new EntityNotFoundException();
        }

        return competitionConverter.convertDtoToModel(competitionDTO);
    }

    public Iterable<Competition> getCompetitions() {
        logger.trace("getCompetitions started --- ");
        var competitionsDTO = restTemplate.getForObject(BASE_URL + "/competitions", CompetitionsDto.class);
        if (competitionsDTO == null) {
            return new ArrayList<>();
        }

        return competitionsDTO.getCompetitionDtoSet().stream().map(competitionDTO -> competitionConverter.convertDtoToModel(competitionDTO)).collect(Collectors.toList());
    }

    public void addCompetition(Competition competition) {
        logger.trace("AddCompetition started -- with {}", competition);
        restTemplate.put(BASE_URL + "/competitions", competitionConverter.convertModelToDto(competition));
        logger.trace("AddCompetition returned ---");
    }

    public void deleteCompetition(int id) {
        logger.trace("DeleteCompetition started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/competitions/" + id);
        logger.trace("DeleteCompetition returned ---");
    }

    public void updateCompetition(Competition competition) {
        logger.trace("UpdateCompetition started -- with {}", competition);
        restTemplate.postForObject(BASE_URL + "/competitions", competitionConverter.convertModelToDto(competition), CompetitionDto.class);
        logger.trace("UpdateCompetition returned ---");
    }

    public List<Competition> getCompetitionWithBiggestPrizePool() {
        logger.trace("getCompetitionWithBiggestPrizePool started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("biggestPrizePool", "true");

        var competitionsDto = restTemplate.getForObject(BASE_URL + "/competitions?biggestPrizePool={biggestPrizePool}", CompetitionsDto.class, parameters);
        if (competitionsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("getCompetitionWithBiggestPrizePool returned ---");
        return competitionsDto.getCompetitionDtoSet().stream().map(competitionDto -> competitionConverter.convertDtoToModel(competitionDto)).collect(Collectors.toList());
    }

    public List<Competition> filterCompetitionByYear(Integer year) {
        logger.trace("filterCompetitionByYear started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstYear", Integer.toString(year));

        var competitionsDto = restTemplate.getForObject(BASE_URL + "/competitions?firstYear={firstYear}", CompetitionsDto.class, parameters);
        if (competitionsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("filterCompetitionByYear returned ---");
        return competitionsDto.getCompetitionDtoSet().stream().map(competitionDto -> competitionConverter.convertDtoToModel(competitionDto)).collect(Collectors.toList());
    }

    public List<Competition> sortCompetitionsByYear() {
        logger.trace("sortCompetitionsByYear started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("sortByFirstYear", "true");

        var competitionsDto = restTemplate.getForObject(BASE_URL + "/competitions?sortByFirstYear={sortByFirstYear}", CompetitionsDto.class, parameters);
        if (competitionsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("sortCompetitionsByYear returned ---");
        return competitionsDto.getCompetitionDtoSet().stream().map(competitionDto -> competitionConverter.convertDtoToModel(competitionDto)).collect(Collectors.toList());
    }
}
