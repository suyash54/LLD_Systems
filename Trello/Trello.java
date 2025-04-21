package Trello;

public class Trello {

    public static void main(String[] args){

        Board board = new Board(1,"Sprint_Board_1");
        BoardController boardController = new BoardController();
        UserStory userStory1 = new UserStory(1,"Login Feature","This is the login feature sprint");
        UserStory userStory2 = new UserStory(2,"Dashboard Feature","This is the dashboard feature");

        board.addUserStory(userStory1);
        board.addUserStory(userStory2);

        Card card1 = new Card(1,"Design Login UI","Implement User Login UI");
        Card card2 = new Card(2, "Design Login Backend","Implement Login Backend");

        Card card3 = new Card(3,"Design Dashboard UI","Implementation of Dashboard UI");
        Card card4 = new Card(4, "Design Dashboard Backend","Implementation of Dashboard backend");

        userStory1.addCard(card1);
        userStory1.addCard(card2);
        userStory2.addCard(card3);
        userStory2.addCard(card4);

        HierarchyDisplayVisitor visitor = new HierarchyDisplayVisitor();
        System.out.println("\n======Viewing Board======\n");
        board.ls(visitor);

        System.out.println("\n======Viewing UserStory 1======\n");
        userStory1.ls(visitor);

        System.out.println("\n======Viewing Card1==========\n");
        card1.ls(visitor);


        boardController.addBoard(board);
        boardController.showAllBoards();
        boardController.deleteBoard(board);

    }
}
