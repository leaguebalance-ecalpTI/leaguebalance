package nerdolas.podres.leaguebalance.model.jogador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RoleJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    private int nota;

    public RoleJogador(Role role, int nota) {
        this.role = role;
        this.nota = nota;
    }
}
