package nerdolas.podres.leaguebalance.controller;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import nerdolas.podres.leaguebalance.dto.Lista;
import nerdolas.podres.leaguebalance.dto.MatchDetailDTO;
import nerdolas.podres.leaguebalance.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final TimeService service;

    public MatchController(TimeService service) {
        this.service = service;
    }

    @PostMapping("new/balanced-team")
    public ResponseEntity<MatchDetailDTO> newBalancedTeam(@RequestBody Lista lista){
        var sum = Summoner.named("Loiz#BR1").withRegion(Region.BRAZIL).get();
        System.out.println(sum);
        return ResponseEntity.ok(service.gerarTimes(lista.playersId()));
    }
}
