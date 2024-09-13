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
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer notaGeral;

    @OneToOne(cascade = CascadeType.ALL)
    private JogadorImage jogadorImage = new JogadorImage();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<RoleJogador> rolesComNota = new ArrayList<>();

    @Transient
    private RoleJogador roleEscolhida;

    public Jogador(DadosJogadorDTO dados){
        this.nome = dados.nickname();
        this.rolesComNota.add(new RoleJogador(Role.JG, dados.notaJg()));
        this.rolesComNota.add(new RoleJogador(Role.MID, dados.notaTop()));
        this.rolesComNota.add(new RoleJogador(Role.SUP, dados.notaMid()));
        this.rolesComNota.add(new RoleJogador(Role.ADC, dados.notaAdc()));
        this.rolesComNota.add(new RoleJogador(Role.TOP, dados.notaSup()));

        this.notaGeral = dados.notaTop() + dados.notaJg() + dados.notaAdc() + dados.notaMid() + dados.notaSup();
    }

    public void setRoleEscolhida(Role role) {
        for (RoleJogador roleJogador: rolesComNota) {
            if(roleJogador.getRole().equals(role)){
                this.roleEscolhida = roleJogador;
            }
        }
    }

    public RoleJogador getRoleJogadorComNota(Role role){
        System.out.println("entrou aqui");
        for (RoleJogador roleJogador : rolesComNota) {
            System.out.println(roleJogador);
            if(roleJogador.getRole().equals(role)){
                return roleJogador;
            }
        }
        return null;
    }
}
