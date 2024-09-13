package nerdolas.podres.leaguebalance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.jogador.Jogador;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer notaTotal;

    @Transient
    private List<Jogador> jogadores = new ArrayList<>();
    public void addPlayer(Jogador player){
        jogadores.add(player);
        this.notaTotal = this.notaTotal + player.getRoleEscolhida().getNota();
    }

}
