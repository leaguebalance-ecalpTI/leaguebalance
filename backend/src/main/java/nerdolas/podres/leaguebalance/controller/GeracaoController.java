package nerdolas.podres.leaguebalance.controller;

import nerdolas.podres.leaguebalance.dto.Lista;
import nerdolas.podres.leaguebalance.dto.MatchDetailDTO;
import nerdolas.podres.leaguebalance.dto.TeamDetailForMatchDTO;
import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.dto.DadosListaJogadoresDTO;
import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.team.Team;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.repository.MatchRepository;
import nerdolas.podres.leaguebalance.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GeracaoController {

    private final TimeService service;

    private final JogadorRepository repository;

    private final MatchRepository matchRepository;
    private final JogadorRepository jogadorRepository;

    public GeracaoController(TimeService service, JogadorRepository repository, MatchRepository matchRepository, JogadorRepository jogadorRepository) {
        this.service = service;
        this.repository = repository;
        this.matchRepository = matchRepository;
        this.jogadorRepository = jogadorRepository;
    }

    @GetMapping("lista-jogadores")
    public ResponseEntity<List<DadosListaJogadoresDTO>> listaJogadores(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(DadosListaJogadoresDTO::new).toList());
    }

    @PostMapping("new/balanced-team")
    public ResponseEntity<MatchDetailDTO> newBalancedTeam(@RequestBody Lista lista){

        Map<String, Team> times = service.gerarTimes(lista.playersId());

        Match match = new Match();

        match.setTeamRedSide(times.get("Time1"));
        match.setTeamBlueSide(times.get("Time2"));

        matchRepository.save(match);

        var playerNamesBlueSide = new ArrayList<String>();
        var playerNamesRedSide = new ArrayList<String>();

        times.get("Time1").getJogadores().forEach(p -> playerNamesBlueSide.add(p.getNome()));

        times.get("Time2").getJogadores().forEach(p -> playerNamesRedSide.add(p.getNome()));

        return ResponseEntity.ok(new MatchDetailDTO(match,
                new TeamDetailForMatchDTO(times.get("Time1"), playerNamesBlueSide), new TeamDetailForMatchDTO(times.get("Time2"), playerNamesRedSide)));
    }


}
