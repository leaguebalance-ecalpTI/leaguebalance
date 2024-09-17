package nerdolas.podres.leaguebalance.repository;

import nerdolas.podres.leaguebalance.model.jogador.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
