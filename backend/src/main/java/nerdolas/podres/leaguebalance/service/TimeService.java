package nerdolas.podres.leaguebalance.service;

import nerdolas.podres.leaguebalance.model.Time;
import nerdolas.podres.leaguebalance.model.jogador.Jogador;
import nerdolas.podres.leaguebalance.model.jogador.Role;
import nerdolas.podres.leaguebalance.model.jogador.RoleJogador;
import nerdolas.podres.leaguebalance.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class TimeService {

    @Autowired
    private JogadorRepository repository;

    public List<Jogador> gerarTimes(List<Long> playersId) {

        Time team1 = new Time();
        Time team2 = new Time();

        List<Jogador> playersList = new ArrayList<>();

        for (Long playerId : playersId) {
            var player = repository.getReferenceById(playerId);
            playersList.add(player);
        }

        while (true) {

            team1.setNotaTotal(0);
            team2.setNotaTotal(0);
            int subtracao = 0;

            List<Jogador> allPlayers = new ArrayList<>(playersList);

            Collections.shuffle(allPlayers);

            Jogador playerJungleRedSide = returnRandomJg(allPlayers, team1);
            Jogador playerTopRedSide = returnRandomPlayer(allPlayers, Role.TOP, team1);
            Jogador playerMidRedSide = returnRandomPlayer(allPlayers, Role.MID, team1);
            Jogador playerAdcRedSide = returnRandomPlayer(allPlayers, Role.ADC, team1);
            Jogador playerSupRedSide = returnRandomPlayer(allPlayers, Role.SUP, team1);

            returnPlayerByRoleAndScore(allPlayers, playerJungleRedSide, team2);
            returnPlayerByRoleAndScore(allPlayers, playerTopRedSide, team2);
            returnPlayerByRoleAndScore(allPlayers, playerMidRedSide, team2);
            returnPlayerByRoleAndScore(allPlayers, playerAdcRedSide, team2);
            returnPlayerByRoleAndScore(allPlayers, playerSupRedSide, team2);

            if (team1.getNotaTotal() > team2.getNotaTotal()) {
                subtracao = team1.getNotaTotal() - team2.getNotaTotal();
            } else {
                subtracao = team2.getNotaTotal() - team1.getNotaTotal();
            }

            if (subtracao <= 1 && subtracao >= 0 && team1.getJogadores().size() == 5 && team2.getJogadores().size() == 5) {

                System.out.println("Nota Time1 = " + team1.getNotaTotal() + "\n");

                for (Jogador player:  team1.getJogadores()) {
                    System.out.println(player.getNome() + " nota: " + player.getRoleEscolhida().getNota());
                }ç
                        

                System.out.println("Nota Time2 = " + team2.getNotaTotal() + "\n");

                for (Jogador player : team2.getJogadores()){
                    System.out.println(player.getNome() + " nota: " + player.getRoleEscolhida().getNota());
                }

                break;
            }

            allPlayers.clear();
            team1.getJogadores().clear();
            team2.getJogadores().clear();
        }
        return playersList;
    }

    public static void returnPlayerByRoleAndScore(List<Jogador> allPlayers, Jogador targetPlayer, Time team) {
        Iterator<Jogador> iterator = allPlayers.iterator();
        while (iterator.hasNext()) {
            Jogador player = iterator.next();
            for (RoleJogador role : player.getRolesComNota()) {
                int diferenca;

                if (role.getNota() > targetPlayer.getRoleEscolhida().getNota()) {
                    diferenca = role.getNota() - targetPlayer.getRoleEscolhida().getNota();
                } else {
                    diferenca = targetPlayer.getRoleEscolhida().getNota() - role.getNota();
                }

                if (role.getRole().equals(targetPlayer.getRoleEscolhida().getRole()) && diferenca < 3) {
                    iterator.remove();
                    player.setRoleEscolhida(targetPlayer.getRoleEscolhida().getRole());
                    team.addPlayer(player);
                }
            }
        }
    }

    public static Jogador returnRandomPlayer(List<Jogador> allPlayers, Role role, Time team) {
        for (Jogador player : allPlayers) {
            for (RoleJogador playerRole : player.getRolesComNota()) {
                if (playerRole.getRole().equals(role)) {
                    player.setRoleEscolhida(role);
                    allPlayers.remove(player);
                    team.addPlayer(player);
                    return player;
                }
            }
        }
        return null;
    }

    public static Jogador returnRandomJg(List<Jogador> allPlayers, Time team) {
        for (Jogador player : allPlayers) {
            for (RoleJogador playerRole : player.getRolesComNota()) {
                if (playerRole.getRole().equals(Role.JG) && playerRole.getNota() >= 5) {
                    player.setRoleEscolhida(Role.JG);
                    allPlayers.remove(player);
                    team.addPlayer(player);
                    return player;
                }
            }
        }
        return null;
    }
}
