package nerdolas.podres.leaguebalance.repository;

import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
