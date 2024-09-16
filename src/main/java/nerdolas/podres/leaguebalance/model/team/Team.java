package nerdolas.podres.leaguebalance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.jogador.Player;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Team {

    private Integer notaTotal;

    @Transient
    private List<Player> jogadores = new ArrayList<>();
    public void addPlayer(Player player){
        jogadores.add(player);
        this.notaTotal = this.notaTotal + player.getRoleEscolhida().getScore();
    }

}
