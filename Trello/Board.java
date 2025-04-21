package Trello;

import java.util.List;
import java.util.stream.Collectors;

public class Board extends CompositeComponent{

    public Board(int id, String name){
        super(id, name);
    }

    public void addUserStory(UserStory userStory){
        addChild(userStory);
    }

    public List<CompositeComponent> getUserStories(){
        return children.stream().filter(child -> child instanceof UserStory)
                .map(child -> (UserStory) child)
                .collect(Collectors.toList());
    }

    public void ls(ComponentVisitor visitor){
          visitor.visitBoard(this);
          super.ls(visitor);
    }


}
