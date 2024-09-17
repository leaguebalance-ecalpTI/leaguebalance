package nerdolas.podres.leaguebalance.model.dto;

import nerdolas.podres.leaguebalance.model.jogador.Jogador;
import nerdolas.podres.leaguebalance.model.jogador.Role;

public record DadosListaJogadoresDTO(

        String nome,
        byte[] imageCardByte,
        String imageCardType,
        int notaGeral,
        int notaJg,
        int notaTop,
        int notaMid,
        int notaAdc,
        int notaSup
) {

    public DadosListaJogadoresDTO(Jogador jogador){
        this(
                jogador.getNome(),
                jogador.getJogadorImage().getImageCardByte(),
                jogador.getJogadorImage().getType(),
                jogador.getNotaGeral(),
                jogador.getRoleJogadorComNota(Role.JG).getNota(),
                jogador.getRoleJogadorComNota(Role.TOP).getNota(),
                jogador.getRoleJogadorComNota(Role.MID).getNota(),
                jogador.getRoleJogadorComNota(Role.ADC).getNota(),
                jogador.getRoleJogadorComNota(Role.SUP).getNota()
        );
    }

}
