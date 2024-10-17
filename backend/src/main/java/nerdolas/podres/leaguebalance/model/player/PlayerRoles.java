package nerdolas.podres.leaguebalance.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_roles")
public class PlayerRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    private Player player;

    private int score;

    public PlayerRoles(Role role, int score) {
        this.role = role;
        this.score = score;
    }
}
