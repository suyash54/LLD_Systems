package Trello;

import java.util.List;
import java.util.stream.Collectors;

public class UserStory extends CompositeComponent{

    private String description;

    public UserStory(int id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public void addCard(Card card) {
        addChild(card);
    }

    public List<Component> getCards() {
         return children.stream()
                 .filter(child -> child instanceof Card)
                 .map(child -> (Card) child)
                 .collect(Collectors.toList());
    }

    public String getDescription() {
        return this.description;
    }

    public void ls(ComponentVisitor visitor) {
          visitor.visitUserStory(this);
          super.ls(visitor);
    }
}
