package nerdolas.podres.leaguebalance.controller;

import jakarta.transaction.Transactional;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import nerdolas.podres.leaguebalance.model.dto.DadosListaJogadoresDTO;
import nerdolas.podres.leaguebalance.service.JogadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {

    final JogadorService service;

    public PlayerController(JogadorService service) {
        this.service = service;
    }

    @PostMapping("new")
    @Transactional
    public ResponseEntity<Long> cadastroJogador(@RequestBody DadosJogadorDTO dados){
        Long jogadorId = service.cadastrarJogador(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorId);
    }

    @GetMapping("list")
    public ResponseEntity<List<DadosListaJogadoresDTO>> playerList(){
        return ResponseEntity.ok().body(service.playerList());
    }

    @PutMapping("cadastro/image/{id}")
    @Transactional
    public ResponseEntity<?> cadastroImagemJogador(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        service.vincularImagem(id, file);
        return ResponseEntity.ok().build();
    }
}
