package nerdolas.podres.leaguebalance.model.team;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalScore;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_player",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> jogadores = new ArrayList<>();

    public void addPlayer(Player player){
        jogadores.add(player);
        this.totalScore = this.totalScore + player.getRoleEscolhida().getScore();
    }

}
