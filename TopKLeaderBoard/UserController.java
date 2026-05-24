package TopKLeaderBoard;

import java.util.*;

public class UserController {

    Map<String,User> userMap;
    Map<String, List<User>> playerToUserList;
    TreeSet<User> userLeaderBoard;


    public UserController(){
        userMap = new HashMap<>();
        playerToUserList = new HashMap<>();
        userLeaderBoard = new TreeSet<>((a,b) -> {
            if(!Objects.equals(a.getUserScore(),b.getUserScore())){
                return b.getUserScore()-a.getUserScore();
            }
            return a.getId().compareTo(b.getId());
        });
    }


    public void addUser(String name,String id){
        User user = new User(name,id);
        userMap.put(id,user);
        userLeaderBoard.add(user);
    }
    public void addPlayerToUser(Player player , String userId){
        User user = userMap.getOrDefault(userId,null);

        if(!Objects.isNull(user)){
            userLeaderBoard.remove(user);
            user.addPlayerToUser(player);
            user.updateUserScore(player.getPlayerScore());
            userLeaderBoard.add(user);
            playerToUserList
                    .computeIfAbsent(player.getPlayerId(),k->new ArrayList<>())
                    .add(user);
        }

    }

    public User getUserByUserId(String userId){
        return userMap.getOrDefault(userId,null);
    }

    public List<User> getTopKUsers(int k){
      List<User> result = new ArrayList<>();

      int count = 0;

      for(User user: userLeaderBoard){
          if(count == k)
              break;
          result.add(user);
          count++;
      }
      return result;
    }

    public void addScore(Player player,int score){
        List<User> users = playerToUserList.getOrDefault(player.getPlayerId(),new ArrayList<>());

        for(User user: users){
            userLeaderBoard.remove(user);
            user.updateUserScore(score);
            userLeaderBoard.add(user);
        }
    }






}
