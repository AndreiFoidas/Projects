package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;

import java.util.List;

public interface IMprService {

    MatchPlayerRelation getMprById(int id);
    Iterable<MatchPlayerRelation> getMprs();
    void addMpr(MatchPlayerRelation matchPlayerRelation);
    void deleteMpr(int id);
    void updateMpr(MatchPlayerRelation matchPlayerRelation);
    List<MatchPlayerRelation> filterMprByGoals(int goals);
    List<MatchPlayerRelation> getMprWithMostGoalsScored();

}
