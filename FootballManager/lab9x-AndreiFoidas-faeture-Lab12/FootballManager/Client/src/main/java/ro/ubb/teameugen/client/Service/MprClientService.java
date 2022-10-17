package ro.ubb.teameugen.client.Service;

/*
@Service
public class MprClientService {
    private final Logger logger = LoggerFactory.getLogger(MprClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MatchPlayerRelationConverter mprConverter;

    public MatchPlayer getMprById(int id) {
        logger.trace("GetMprById entered -- id = {}", id);
        var mprDto=restTemplate.getForObject(BASE_URL + "/mprs/" + id, MatchPlayerRelationDto.class);

        if(mprDto == null){
            throw new EntityNotFoundException();
        }

        return this.mprConverter.convertDtoToModel(mprDto);
    }

    public Iterable<MatchPlayer> getMprs() {
        logger.trace("GetMprs started --- ");
        var mprsDto=restTemplate.getForObject(BASE_URL + "/mprs", MatchPlayerRelationsDto.class);

        if(mprsDto == null){
            return new ArrayList<>();
        }

        return mprsDto.getMprDtoSet().stream().
                map(mprDto->this.mprConverter.convertDtoToModel(mprDto))
                .collect(Collectors.toList());
    }

    public void addMpr(MatchPlayer matchPlayer) {
        logger.trace("AddMpr started --- ");
        restTemplate.put(BASE_URL + "/mprs", this.mprConverter.convertModelToDto(matchPlayer));
        logger.trace("AddMpr returned --- ");
    }

    public void deleteMpr(int id) {
        logger.trace("DeleteMpr started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/mprs/" + id);
        logger.trace("DeleteMpr returned ---");
    }

    public void updateMpr(MatchPlayer matchPlayer) {
        logger.trace("UpdateMpr started -- with {}", matchPlayer);
        restTemplate.postForObject(BASE_URL + "/mprs", this.mprConverter.convertModelToDto(matchPlayer), MatchPlayerRelationDto.class);
        logger.trace("UpdateMpr returned ---");
    }

    public List<MatchPlayer> filterMprByGoals(int goals) {
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

    public List<MatchPlayer> getMprWithMostGoalsScored() {
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
*/