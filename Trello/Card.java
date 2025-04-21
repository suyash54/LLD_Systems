package Trello;

public class Card implements Component{

    private int id;
    private String name;
    private String description;
    private Component parent;

    public Card(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public void delete(){

        if(parent!= null && parent instanceof CompositeComponent){
            ((CompositeComponent) parent).removeChild(this);
        }
        System.out.println("Card deleted \n"+this.name );
    }

    public Component getParent(){
        return this.parent;
    }

    public void setParent(Component parent){
        this.parent = parent;
    }

    public void ls(ComponentVisitor visitor){
        visitor.visitCard(this);
    }
}
