package nerdolas.podres.leaguebalance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.team.Team;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_red_side_id")
    private Team teamRedSide;

    @ManyToOne
    @JoinColumn(name = "team_blue_side_id")
    private Team teamBlueSide;

}