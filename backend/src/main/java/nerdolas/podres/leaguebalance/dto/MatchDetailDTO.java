package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.Match;

import java.util.List;

public record MatchDetailDTO(
        Long id,
        TeamDetailForMatchDTO teamRedSide,
        TeamDetailForMatchDTO teamBlueSide
) {
    public MatchDetailDTO(Match match) {
        this(
                match.getId(),
                new TeamDetailForMatchDTO(match.getTeamRedSide()),
                new TeamDetailForMatchDTO(match.getTeamBlueSide())
        );
    }
}

