package nerdolas.podres.leaguebalance.model.team;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.player.PlayerRoles;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalScore;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_player",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_roles_id"))
    private List<PlayerRoles> jogadores = new ArrayList<>();

    public void addPlayerRole(PlayerRoles player){
        jogadores.add(player);
        if(player.getPlayer().getRoleEscolhida() != null)
            this.totalScore = this.totalScore + player.getPlayer().getRoleEscolhida().getScore();
    }

    public Team(){
        this.totalScore = 0;
    }
}
