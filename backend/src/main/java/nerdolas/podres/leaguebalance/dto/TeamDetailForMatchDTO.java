package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.team.Team;

import java.util.List;

public record TeamDetailForMatchDTO(
        Long id,
        List<String> playerRole,
        Integer totalScore
) {
    public TeamDetailForMatchDTO(Team team, List<String> playerRole) {
        this(team.getId(), playerRole, team.getTotalScore());
    }
}
