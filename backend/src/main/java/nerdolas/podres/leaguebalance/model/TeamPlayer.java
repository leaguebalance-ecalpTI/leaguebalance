package nerdolas.podres.leaguebalance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import nerdolas.podres.leaguebalance.model.team.Team;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team_player")
@Getter
@Setter
@NoArgsConstructor
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "player")
    private PlayerRoles roles;

}
