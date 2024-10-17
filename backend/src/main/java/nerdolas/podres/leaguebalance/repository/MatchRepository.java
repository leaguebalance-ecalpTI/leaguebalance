package nerdolas.podres.leaguebalance.repository;

import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
