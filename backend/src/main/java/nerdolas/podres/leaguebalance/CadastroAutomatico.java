package nerdolas.podres.leaguebalance;

import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import nerdolas.podres.leaguebalance.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CadastroAutomatico implements ApplicationRunner {

	@Autowired
	JogadorService playerService;

	@Override
	public void run(ApplicationArguments args) {
		try{

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Meliodas",
					0,
					2,
					3,
					0,
					1));
			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Gabriel",
					2,
					3,
					4,
					2,
					3));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Danilo",
					2,
					3,
					4,
					2,
					5));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Antonio",
					2,
					5,
					4,
					3,
					4));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Fomfom",
					4,
					5,
					4,
					2,
					4));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Felype",
					5,
					5,
					4,
					3,
					5));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Luis",
					2,
					5,
					4,
					4,
					4));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Breno",
					6,
					10,
					8,
					8,
					6));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Lucas",
					10,
					7,
					8,
					7,
					6));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Vitor",
					8,
					8,
					8,
					10,
					7));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Gustavo",
					7,
					6,
					6,
					6,
					10));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Lemos",
					6,
					6,
					8,
					7,
					6));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Lemos",
					6,
					6,
					8,
					7,
					6));

			playerService.cadastrarJogador(new DadosJogadorDTO(
					"Mochila",
					10,
					5,
					6,
					5,
					7));

		}catch (Exception e){
			throw e;
		}
	}
}
