package nerdolas.podres.leaguebalance.dto;

import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import nerdolas.podres.leaguebalance.model.player.Role;

public record PlayerRoleDetailDTO(
        Role role,
        String playerName,
        int score
) {
    public PlayerRoleDetailDTO(PlayerRoles playerRole) {
        this(
                playerRole.getRole(),
                playerRole.getPlayer().getNome(),
                playerRole.getScore()
        );
    }
}

