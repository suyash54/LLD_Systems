package Trello;

public class HierarchyDisplayVisitor implements ComponentVisitor{

    public void visitBoard(Board board){
        System.out.println("Board: "+ board.getName()+" BoardId: "+ board.getId()+"\n");
    }

    public void visitUserStory(UserStory userStory){
        System.out.println("UserStory: "+ userStory.getName()+" UserStoryId: "+ userStory.getId()+"Description: "+ userStory.getDescription()+"\n");
    }

    public void visitCard(Card card){
        System.out.println("Card: "+ card.getName()+" CardId: "+ card.getId() + "\n");
    }
}
