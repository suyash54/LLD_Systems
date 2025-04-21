package Trello;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private List<Board> boards = new ArrayList<>();

    private CompositeComponent compositeComponent;



    public void showAllBoards(){
        for(Board board: boards) {
            board.ls(new HierarchyDisplayVisitor());
        }
    }

    public void addBoard(Board board){
        boards.add(board);
    }

    public void deleteBoard(Board board){
      boards.remove(board);
      board.delete();
    }

}
