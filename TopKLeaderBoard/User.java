package TopKLeaderBoard;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String id;
    private List<Player> playerList;
    private Integer userScore;

    public User(String name,String id){
        this.name = name;
        this.id = id;
        this.playerList = new ArrayList<>();
        this.userScore = 0;
    }

    public String getName(){return this.name;}

    public String getId(){return this.id;}

    public void addPlayerToUser(Player player){
        this.playerList.add(player);
    }

    public List<Player> getPlayers() { return this.playerList;}

    public Integer getUserScore(){ return this.userScore;}

    public void updateUserScore(int score){ this.userScore+=score; }

}
