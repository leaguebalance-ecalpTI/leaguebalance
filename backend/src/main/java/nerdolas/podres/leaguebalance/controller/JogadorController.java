package nerdolas.podres.leaguebalance.controller;

import jakarta.transaction.Transactional;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import nerdolas.podres.leaguebalance.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("jogadores")
public class JogadorController {

    final JogadorService service;
    public JogadorController(JogadorService service) {
        this.service = service;
    }

    @PostMapping("cadastro")
    @Transactional
    public ResponseEntity<Long> cadastroJogador(@RequestBody DadosJogadorDTO dados){
        Long jogadorId = service.cadastrarJogador(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorId);
    }

    @PutMapping("cadastro/image/{id}")
    @Transactional
    public ResponseEntity<?> cadastroImagemJogador(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        service.vincularImagem(id, file);
        return ResponseEntity.ok().build();
    }
}
