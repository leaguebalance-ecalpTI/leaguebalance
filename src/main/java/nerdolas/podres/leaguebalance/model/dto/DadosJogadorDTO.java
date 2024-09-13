package nerdolas.podres.leaguebalance.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosJogadorDTO(

        @NotBlank String nickname,
        @NotNull int notaJg,
        @NotNull int notaTop,
        @NotNull int notaMid,
        @NotNull int notaAdc,
        @NotNull int notaSup

) {



}
