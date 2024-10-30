package nerdolas.podres.leaguebalance.controller;

import nerdolas.podres.leaguebalance.dto.Lista;
import nerdolas.podres.leaguebalance.dto.MatchDetailDTO;
import nerdolas.podres.leaguebalance.dto.TeamDetailForMatchDTO;
import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.dto.DadosListaJogadoresDTO;
import nerdolas.podres.leaguebalance.model.team.Team;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.repository.MatchRepository;
import nerdolas.podres.leaguebalance.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    private final TimeService service;

    public MatchController(TimeService service) {
        this.service = service;
    }

    @PostMapping("new/balanced-team")
    public ResponseEntity<MatchDetailDTO> newBalancedTeam(@RequestBody Lista lista){
        return ResponseEntity.ok(service.gerarTimes(lista.playersId()));
    }
}
