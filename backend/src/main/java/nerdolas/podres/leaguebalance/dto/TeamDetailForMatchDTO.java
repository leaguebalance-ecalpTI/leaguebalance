package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.team.Team;

import java.util.List;

public record TeamDetailForMatchDTO(
        Long id,
        List<String> players,
        Integer totalScore
) {
    public TeamDetailForMatchDTO(Team team, List<String> playerNames) {
        this(team.getId(), playerNames, team.getTotalScore());
    }
}
