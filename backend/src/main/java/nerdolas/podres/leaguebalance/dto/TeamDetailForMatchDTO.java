package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.team.Team;

import java.util.List;

public record TeamDetailForMatchDTO(
        List<PlayerRoleDetailDTO> players
) {
    public TeamDetailForMatchDTO(Team team) {
        this(
                team.getJogadores().stream()
                        .map(PlayerRoleDetailDTO::new)
                        .toList()
        );
    }
}

