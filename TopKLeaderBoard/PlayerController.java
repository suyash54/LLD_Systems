package TopKLeaderBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerController {

    Map<String,Player> playerMap;
    UserController userController;

    public PlayerController(UserController userController){
        this.userController = userController;
        this.playerMap = new HashMap<>();
    }

    public void addPlayer(String playerId){
        Player player = new Player(playerId);
        playerMap.put(playerId,player);
    }

    public void addPlayerToUser(String playerId,String userId){
        Player player = playerMap.getOrDefault(playerId,null);
        if(!Objects.isNull(player)){
            userController.addPlayerToUser(player,userId);
        }
    }

    public void addPlayerScore(int playerId,int score){
        Player player =  playerMap.getOrDefault(playerId,null);
        if(!Objects.isNull(player)){
            player.updatePlayerScore(score);
            userController.addScore(player,score);
        }
    }
}
