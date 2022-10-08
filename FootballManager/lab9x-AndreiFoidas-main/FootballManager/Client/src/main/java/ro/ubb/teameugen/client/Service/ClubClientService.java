package ro.ubb.teameugen.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.common.Converter.ClubConverter;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.DTOs.ClubsDto;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClubClientService {
    private final Logger logger = LoggerFactory.getLogger(ClubClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClubConverter clubConverter;

    public Club getClubById(int id) {
        logger.trace("GetClubById started --- with id {}", id);
        var clubDto = restTemplate.getForObject(BASE_URL + "/clubs/" + id, ClubDto.class);

        if (clubDto == null) {
            throw new EntityNotFoundException();
        }

        return clubConverter.convertDtoToModel(clubDto);
    }

    public Iterable<Club> getClubs() {
        logger.trace("GetClubs started --- ");
        var clubsDto = restTemplate.getForObject(BASE_URL + "/clubs", ClubsDto.class);

        if (clubsDto == null) {
            return new ArrayList<>();
        }

        return clubsDto.getClubDtoSet().stream().map(clubDto -> clubConverter.convertDtoToModel(clubDto)).collect(Collectors.toList());
    }

    public void addClub(Club club) {
        logger.trace("AddClub started -- with {}", club);
        restTemplate.put(BASE_URL + "/clubs", clubConverter.convertModelToDto(club));
        logger.trace("AddClub returned ---");
    }

    public void deleteClub(int id) {
        logger.trace("DeleteClub started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/clubs/" + id);
        logger.trace("DeleteClub returned ---");
    }

    public void updateClub(Club club) {
        logger.trace("UpdateClub started -- with {}", club);
        restTemplate.postForObject(BASE_URL + "/clubs", clubConverter.convertModelToDto(club), ClubDto.class);
        logger.trace("UpdateClub returned ---");
    }

    public List<Club> filterClubsByOwner(String ownerName) {
        logger.trace("filterClubsByOwner started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("owner", ownerName);

        var clubsDto = restTemplate.getForObject(BASE_URL + "/club?owner={owner}", ClubsDto.class, parameters);

        if (clubsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("filterClubsByOwner returned ---");
        return clubsDto.getClubDtoSet().stream().map(clubDto -> clubConverter.convertDtoToModel(clubDto)).collect(Collectors.toList());

    }

    public List<Club> filterClubsByCountry(String country) {
        logger.trace("filterClubsByCountry started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("country", country);

        var clubsDto = restTemplate.getForObject(BASE_URL + "/clubs?country={country}", ClubsDto.class, parameters);

        if (clubsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("filterClubsByCountry returned ---");
        return clubsDto.getClubDtoSet().stream().map(clubDto -> clubConverter.convertDtoToModel(clubDto)).collect(Collectors.toList());

    }

    public List<Club> getClubWithBiggestBudget() {
        logger.trace("GetClubWithBiggestBudget started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("maxBudget", "true");

        var clubsDto = restTemplate.getForObject(BASE_URL + "/clubs?maxBudget={maxBudget}", ClubsDto.class, parameters);

        if (clubsDto == null) {
            return new ArrayList<>();
        }

        logger.trace("GetClubWithBiggestBudget returned ---");
        return clubsDto.getClubDtoSet().stream().map(clubDto -> clubConverter.convertDtoToModel(clubDto)).collect(Collectors.toList());
    }

    public void deleteClubByCountry(String country) {
        logger.trace("DeleteClubByCountry started -- with country {}", country);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("country", country);
        restTemplate.delete(BASE_URL + "/clubs?country={country}", parameters);

        logger.trace("DeleteClub returned ---");
    }

}
