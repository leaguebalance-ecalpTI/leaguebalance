package nerdolas.podres.leaguebalance.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerdolas.podres.leaguebalance.model.player.Player;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JogadorImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    Player player;

    String nome;

    String type;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageCardByte;

}
