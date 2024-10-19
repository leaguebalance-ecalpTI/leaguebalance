package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.Match;

import java.util.List;

public record MatchDetailDTO(
    Long id,
    TeamDetailForMatchDTO teamRedSide,
    TeamDetailForMatchDTO temBlueside
) {
    public MatchDetailDTO(Match match, TeamDetailForMatchDTO teamRedSide, TeamDetailForMatchDTO temBlueside){
        this(match.getId(), teamRedSide, temBlueside);
    }
}
