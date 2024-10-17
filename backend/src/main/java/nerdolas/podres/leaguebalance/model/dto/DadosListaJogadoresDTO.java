package nerdolas.podres.leaguebalance.model.dto;

import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.player.Role;

public record DadosListaJogadoresDTO(

        String nome,
//        byte[] imageCardByte,
//        String imageCardType,
        int notaGeral,
        int notaJg,
        int notaTop,
        int notaMid,
        int notaAdc,
        int notaSup
) {

    public DadosListaJogadoresDTO(Player player){
        this(
                player.getNome(),
//                player.getJogadorImage().getImageCardByte(),
//                player.getJogadorImage().getType(),
                player.getTotalScore(),
                player.getRoleJogadorComNota(Role.JG).getScore(),
                player.getRoleJogadorComNota(Role.TOP).getScore(),
                player.getRoleJogadorComNota(Role.MID).getScore(),
                player.getRoleJogadorComNota(Role.ADC).getScore(),
                player.getRoleJogadorComNota(Role.SUP).getScore()
        );
    }

}
