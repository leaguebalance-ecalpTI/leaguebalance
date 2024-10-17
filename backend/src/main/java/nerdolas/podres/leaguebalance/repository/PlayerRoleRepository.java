package nerdolas.podres.leaguebalance.repository;

import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRoleRepository extends JpaRepository<PlayerRoles, Long> {

}
