package nerdolas.podres.leaguebalance.service;

import nerdolas.podres.leaguebalance.model.jogador.Jogador;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class JogadorService {

    @Autowired
    JogadorRepository repository;

    public Long cadastrarJogador(DadosJogadorDTO dados){
        var jogador = new Jogador(dados);
        Jogador jogadorSalvo = repository.save(jogador);
        return jogadorSalvo.getId();
    }

    public void vincularImagem(Long id, MultipartFile image){

        var jogador = repository.getReferenceById(id);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            IOUtils.copy(image.getInputStream(), os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        jogador.getJogadorImage().setImageCardByte(os.toByteArray());
        jogador.getJogadorImage().setType(image.getContentType());
        jogador.getJogadorImage().setNome(image.getOriginalFilename());
        jogador.getJogadorImage().setJogador(jogador);
    }

}
