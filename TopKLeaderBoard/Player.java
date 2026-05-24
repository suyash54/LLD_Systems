package TopKLeaderBoard;

public class Player {

    String playerId;
    int score;

    public Player(String playerId){
        this.playerId = playerId;
        this.score  = 0;
    }

    public int getPlayerScore(){
        return this.score;
    }

    public void  updatePlayerScore(int score ){
        this.score += score;
    }

    public String getPlayerId(){
        return this.playerId;
    }

}
