package nerdolas.podres.leaguebalance.service;

import jakarta.transaction.Transactional;
import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.dto.DadosJogadorDTO;
import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import nerdolas.podres.leaguebalance.model.player.Role;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.repository.PlayerRoleRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class JogadorService {

    final JogadorRepository repository;
    private final PlayerRoleRepository playerRoleRepository;

    public JogadorService(JogadorRepository repository, PlayerRoleRepository playerRoleRepository) {
        this.repository = repository;
        this.playerRoleRepository = playerRoleRepository;
    }

    @Transactional
    public Long cadastrarJogador(DadosJogadorDTO dados){

        var player = new Player(dados);

        player.getRoles().add(createRole(Role.JG, dados.notaJg(), player));
        player.getRoles().add(createRole(Role.MID, dados.notaTop(), player));
        player.getRoles().add(createRole(Role.SUP, dados.notaMid(), player));
        player.getRoles().add(createRole(Role.ADC, dados.notaAdc(), player));
        player.getRoles().add(createRole(Role.TOP, dados.notaSup(), player));

        Player playerSalvo = repository.save(player);
        return playerSalvo.getId();
    }

    public PlayerRoles createRole(Role role, int nota, Player player) {
        PlayerRoles playerRoles = new PlayerRoles(role, nota);
        playerRoles.setPlayer(player);
        playerRoleRepository.save(playerRoles);
        return playerRoles;
    }

    public void setRoleEscolhida(Player player, Role role) {
        for (PlayerRoles playerRoles : player.getRoles()) {
            if(playerRoles.getRole().equals(role)){
                player.setRoleEscolhida(playerRoles);
            }
        }
    }

    public void vincularImagem(Long id, MultipartFile image){

        var jogador = repository.getReferenceById(id);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            IOUtils.copy(image.getInputStream(), os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        jogador.getJogadorImage().setImageCardByte(os.toByteArray());
//        jogador.getJogadorImage().setType(image.getContentType());
//        jogador.getJogadorImage().setNome(image.getOriginalFilename());
//        jogador.getJogadorImage().setPlayer(jogador);
    }

}
