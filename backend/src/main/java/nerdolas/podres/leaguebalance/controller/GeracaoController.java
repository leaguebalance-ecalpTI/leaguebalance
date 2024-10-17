package nerdolas.podres.leaguebalance.controller;

import nerdolas.podres.leaguebalance.dto.Lista;
import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.dto.DadosListaJogadoresDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GeracaoController {

    private final TimeService service;

    private final JogadorRepository repository;

    private final MatchRepository matchRepository;

    public GeracaoController(TimeService service, JogadorRepository repository, MatchRepository matchRepository) {
        this.service = service;
        this.repository = repository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("lista-jogadores")
    public ResponseEntity<?> listaJogadores(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(DadosListaJogadoresDTO::new).toList());
    }

    @PostMapping("new/balanced-team")
    public ResponseEntity<?> newBalancedTeam(@RequestBody Lista lista){

        Map<String, Team> times = service.gerarTimes(lista.playersId());

        Match match = new Match();

        match.setTeamRedSide(times.get("Time1"));
        match.setTeamBlueSide(times.get("Time2"));

        matchRepository.save(match);

        return ResponseEntity.ok().build();
    }

}
