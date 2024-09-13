package nerdolas.podres.leaguebalance.controller;

import nerdolas.podres.leaguebalance.dto.Lista;
import nerdolas.podres.leaguebalance.model.dto.DadosListaJogadoresDTO;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeracaoController {

    @Autowired
    private TimeService service;

    @Autowired
    private JogadorRepository repository;

    @GetMapping("lista-jogadores")
    public ResponseEntity<?> listaJogadores(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(DadosListaJogadoresDTO::new).toList());
    }

    @PostMapping("new/balanced-team")
    public ResponseEntity<?> newBalancedTeam(@RequestBody Lista lista){

        service.gerarTimes(lista.playersId());

        return ResponseEntity.ok().build();
    }

}
