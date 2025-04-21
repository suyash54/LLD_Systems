package Trello;

import Splitwise.User;

public interface ComponentVisitor {

    void visitBoard(Board board);
    void visitUserStory(UserStory userStory);
    void visitCard(Card card);
}
