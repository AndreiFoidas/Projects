package ro.ubb.teameugen.server.Repository;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;

import java.util.List;

public interface MatchPlayerRelationRepository extends IRepository<MatchPlayerRelation, Integer> {
    List<MatchPlayerRelation> findAllByGoalsScored(int goalsScored);
    List<MatchPlayerRelation> findFirstByOrderByGoalsScoredDesc();
}
