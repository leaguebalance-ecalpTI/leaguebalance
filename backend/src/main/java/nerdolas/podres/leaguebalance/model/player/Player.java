package nerdolas.podres.leaguebalance.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer totalScore;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerRoles> roles = new ArrayList<>();

    @Transient
    private PlayerRoles roleEscolhida;

    public Player(DadosJogadorDTO dados){
        this.nome = dados.nickname();

        this.totalScore = dados.notaTop() + dados.notaJg() + dados.notaAdc() + dados.notaMid() + dados.notaSup();
    }

    public PlayerRoles getRoleJogadorComNota(Role role){
        for (PlayerRoles playerRoles : this.roles) {
            if(playerRoles.getRole().equals(role)){
                return playerRoles;
            }
        }
        return null;
    }
}
