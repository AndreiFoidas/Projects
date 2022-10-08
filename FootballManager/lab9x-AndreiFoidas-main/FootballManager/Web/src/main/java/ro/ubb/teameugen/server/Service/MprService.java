package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.MatchPlayerRelationRepository;
import ro.ubb.teameugen.server.Repository.MatchRepository;
import ro.ubb.teameugen.server.Repository.PlayerRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IMprService;

import java.util.List;

@Service
public class MprService implements IMprService {
    private final Logger logger = LoggerFactory.getLogger(MprService.class);

    @Autowired
    private MatchPlayerRelationRepository mprRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BaseValidator<MatchPlayerRelation> mprValidator;

    @Override
    public MatchPlayerRelation getMprById(int id) {
        logger.trace("GetMprById entered -- id = {}", id);

        var result = mprRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        logger.trace("GetMprById returned -- {}", result);
        return result;
    }

    @Override
    public Iterable<MatchPlayerRelation> getMprs() {
        logger.trace("GetMprs entered ---");

        var result = mprRepository.findAll();

        logger.trace("GetMprs returned -- {}", result);
        return result;
    }

    @Override
    public void addMpr(MatchPlayerRelation matchPlayerRelation) {
        logger.trace("AddMpr entered -- with MatchPlayerRelation {}", matchPlayerRelation);

        if(this.matchRepository.findById(matchPlayerRelation.getMatchID()).isPresent() && this.playerRepository.findById(matchPlayerRelation.getPlayerID()).isPresent()){
            this.mprValidator.validate(matchPlayerRelation);
            this.mprRepository.save(matchPlayerRelation);
            logger.trace("AddMpr returned --- MatchPlayerRelation added");
        }

        logger.trace("AddMpr returned --- failed because Match or Player does not exist");
    }

    @Override
    public void deleteMpr(int id) {
        logger.trace("DeleteMpr entered -- with id = {}", id);

        mprRepository.deleteById(id);

        logger.trace("DeleteMpr returned -- MatchPlayerRelation deleted");
    }

    @Override
    public void updateMpr(MatchPlayerRelation matchPlayerRelation) {
        logger.trace("UpdateMpr entered -- with MatchPlayerRelation {}", matchPlayerRelation);

        this.mprValidator.validate(matchPlayerRelation);
        if (this.matchRepository.findById(matchPlayerRelation.getMatchID()).isPresent() && this.playerRepository.findById(matchPlayerRelation.getPlayerID()).isPresent()) {
            mprRepository.findById(matchPlayerRelation.getId())
                    .ifPresent(c->{
                        mprRepository.save(matchPlayerRelation);
                    });
        }

        logger.trace("UpdateMpr returned -- MatchPlayerRelation updated");
    }

    @Override
    public List<MatchPlayerRelation> filterMprByGoals(int goals) {
        logger.trace("FilterMprByGoals entered -- with goals: {}", goals);
        var result = mprRepository.findAllByGoalsScored(goals);
        logger.trace("FilterMprByGoals returned -- result {}", result);
        return result;
    }

    @Override
    public List<MatchPlayerRelation> getMprWithMostGoalsScored() {
        logger.trace("GetMprWithMostGoalsScored entered ---");
        var result = mprRepository.findFirstByOrderByGoalsScoredDesc();
        logger.trace("GetMprWithMostGoalsScored returned -- result {}", result);
        return result;
    }
}
