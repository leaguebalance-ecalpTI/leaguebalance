package nerdolas.podres.leaguebalance.repository;

import nerdolas.podres.leaguebalance.model.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}
