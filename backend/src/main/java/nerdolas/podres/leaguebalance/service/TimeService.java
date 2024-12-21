package nerdolas.podres.leaguebalance.service;

import com.merakianalytics.orianna.types.dto.summoner.Summoner;
import nerdolas.podres.leaguebalance.dto.MatchDetailDTO;
import nerdolas.podres.leaguebalance.dto.PlayerRoleDetailDTO;
import nerdolas.podres.leaguebalance.dto.TeamDetailForMatchDTO;
import nerdolas.podres.leaguebalance.model.Match;
import nerdolas.podres.leaguebalance.model.team.Team;
import nerdolas.podres.leaguebalance.model.player.Player;
import nerdolas.podres.leaguebalance.model.player.Role;
import nerdolas.podres.leaguebalance.model.player.PlayerRoles;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import nerdolas.podres.leaguebalance.repository.MatchRepository;
import nerdolas.podres.leaguebalance.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TimeService {

    private final JogadorRepository repository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final JogadorService jogadorService;

    public TimeService(JogadorRepository repository,
                       TeamRepository teamRepository,
                       MatchRepository matchRepository,
                       JogadorService jogadorService) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.jogadorService = jogadorService;
    }

    public MatchDetailDTO gerarTimes(List<Long> playersId) {

        List<Player> playersList = new ArrayList<>();

        playersId.forEach(pId -> playersList.add(repository.findById(pId).orElseThrow(RuntimeException::new)));

        Map<String, Team> times = new HashMap<>();

        int attempts = 0;

        while (true) {

            if(attempts == 30){
                throw new RuntimeException("30 attempts achieved");
            }

            Team team1 = new Team();
            Team team2 = new Team();

            Integer scoreDifference = 0;

            var allPlayers = new ArrayList<>(playersList);
            Collections.shuffle(allPlayers);

            setPlayerInTeams(team1, team2, allPlayers);

            scoreDifference = Math.abs(team1.getTotalScore() - team2.getTotalScore());

            if (scoreDifference <= 1 && scoreDifference >= 0 &&
                    team1.getJogadores().size() == 5 && team2.getJogadores().size() == 5) {

                teamRepository.save(team1);
                teamRepository.save(team2);
                times.put("Time1", team1);
                times.put("Time2", team2);

                break;
            }

            attempts++;
        }

        Match match = new Match(times.get("Time1"), times.get("Time2"));

        matchRepository.save(match);

        return new MatchDetailDTO(match);
    }

    public void setPlayerInTeams(Team team1, Team team2, List<Player> allPlayers){
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
                    team.addPlayerRole(role);
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
                    team.addPlayerRole(playerRoles);
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
                    team.addPlayerRole(playerRoles);
                    return player;
                }
            }
        }
        return null;
    }
}
