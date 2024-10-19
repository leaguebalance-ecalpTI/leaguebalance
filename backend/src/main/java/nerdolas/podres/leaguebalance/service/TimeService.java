package nerdolas.podres.leaguebalance.service;

import nerdolas.podres.leaguebalance.model.team.Team;
import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.player.Role;
import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TimeService {

    private final JogadorRepository repository;
    private final TeamRepository teamRepository;
    private final JogadorService jogadorService;

    public TimeService(JogadorRepository repository,
                       TeamRepository teamRepository,
                       JogadorService jogadorService) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.jogadorService = jogadorService;
    }

    public Map<String, Team> gerarTimes(List<Long> playersId) {

        Team team1 = new Team();
        Team team2 = new Team();

        List<Player> playersList = new ArrayList<>();

        for (Long playerId : playersId) {
            var player = repository.findById(playerId).orElseThrow(RuntimeException::new);
            playersList.add(player);
        }

        while (true) {

            team1.setTotalScore(0);
            team2.setTotalScore(0);
            int subtracao = 0;
            int tries = 0;

            List<Player> allPlayers = new ArrayList<>(playersList);

            Collections.shuffle(allPlayers);

            Player playerJungleRedSide = returnRandomJg(allPlayers, team1);
            Player playerTopRedSide = returnRandomPlayer(allPlayers, Role.TOP, team1);
            Player playerMidRedSide = returnRandomPlayer(allPlayers, Role.MID, team1);
            Player playerAdcRedSide = returnRandomPlayer(allPlayers, Role.ADC, team1);
            Player playerSupRedSide = returnRandomPlayer(allPlayers, Role.SUP, team1);

            setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(allPlayers, playerJungleRedSide, team2);
            setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(allPlayers, playerTopRedSide, team2);
            setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(allPlayers, playerMidRedSide, team2);
            setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(allPlayers, playerAdcRedSide, team2);
            setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(allPlayers, playerSupRedSide, team2);

            if (team1.getTotalScore() > team2.getTotalScore()) {
                subtracao = team1.getTotalScore() - team2.getTotalScore();
            } else {
                subtracao = team2.getTotalScore() - team1.getTotalScore();
            }

            if (subtracao <= 1 && subtracao >= 0 && team1.getJogadores().size() == 5 && team2.getJogadores().size() == 5) {

                teamRepository.save(team1);
                teamRepository.save(team2);

                Map<String, Team> times = new HashMap<>();

                times.put("Time1", team1);
                times.put("Time2", team2);

                return times;
            }

            tries++;
            allPlayers.clear();
            team1.getJogadores().clear();
            team2.getJogadores().clear();
        }
    }

    public void setPlayerInTeamTwoByRoleAndScoreOfPlayerInTeamOne(List<Player> allPlayers, Player targetPlayer, Team team) {
        Iterator<Player> iterator = allPlayers.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            for (PlayerRoles role : player.getRoles()) {
                int diferenca;

                if (role.getScore() > targetPlayer.getRoleEscolhida().getScore()) {
                    diferenca = role.getScore() - targetPlayer.getRoleEscolhida().getScore();
                } else {
                    diferenca = targetPlayer.getRoleEscolhida().getScore() - role.getScore();
                }

                if (role.getRole().equals(targetPlayer.getRoleEscolhida().getRole()) && diferenca < 3) {
                    iterator.remove();
                    this.jogadorService.setRoleEscolhida(player, targetPlayer.getRoleEscolhida().getRole());
                    team.addPlayer(player);
                }
            }
        }
    }

    public Player returnRandomPlayer(List<Player> allPlayers, Role role, Team team) {
        for (Player player : allPlayers) {
            for (PlayerRoles playerRoles : player.getRoles()) {
                if (playerRoles.getRole().equals(role)) {
                    this.jogadorService.setRoleEscolhida(player, role);
                    allPlayers.remove(player);
                    team.addPlayer(player);
                    return player;
                }
            }
        }
        return null;
    }

    public Player returnRandomJg(List<Player> allPlayers, Team team) {
        for (Player player : allPlayers) {
            for (PlayerRoles playerRoles : player.getRoles()) {
                if (playerRoles.getRole().equals(Role.JG) && playerRoles.getScore() >= 5) {
                    this.jogadorService.setRoleEscolhida(player, Role.JG);
                    allPlayers.remove(player);
                    team.addPlayer(player);
                    return player;
                }
            }
        }
        return null;
    }
}
