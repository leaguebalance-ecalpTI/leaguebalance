package nerdolas.podres.leaguebalance.model.jogador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.JogadorImage;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;

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

    private Integer notaGeral;

    @OneToOne(cascade = CascadeType.ALL)
    private JogadorImage jogadorImage = new JogadorImage();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_roles", joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<PlayerRole> roles = new ArrayList<>();

    @Transient
    private PlayerRole roleEscolhida;

    public Player(DadosJogadorDTO dados){
        this.nome = dados.nickname();
        this.roles.add(new PlayerRole(Role.JG, dados.notaJg()));
        this.roles.add(new PlayerRole(Role.MID, dados.notaTop()));
        this.roles.add(new PlayerRole(Role.SUP, dados.notaMid()));
        this.roles.add(new PlayerRole(Role.ADC, dados.notaAdc()));
        this.roles.add(new PlayerRole(Role.TOP, dados.notaSup()));

        this.notaGeral = dados.notaTop() + dados.notaJg() + dados.notaAdc() + dados.notaMid() + dados.notaSup();
    }

    public void setRoleEscolhida(Role role) {
        for (PlayerRole playerRole : this.roles) {
            if(playerRole.getRole().equals(role)){
                this.roleEscolhida = playerRole;
            }
        }
    }

    public PlayerRole getRoleJogadorComNota(Role role){
        for (PlayerRole playerRole : this.roles) {
            if(playerRole.getRole().equals(role)){
                return playerRole;
            }
        }
        return null;
    }
}
