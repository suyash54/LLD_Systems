package Trello;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeComponent implements Component {

    private int id;
    private String name;
    private Component parent;
    protected List<Component> children = new ArrayList<>();

    public CompositeComponent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public CompositeComponent getComponentById(int id){
        if(id == this.id){
            return this;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setParent(Component component) {
        this.parent = component;
    }

    public Component getParent() {
        return this.parent;
    }

    public void addChild(Component child) {
        if (child instanceof CompositeComponent) {
            CompositeComponent compositeChild = (CompositeComponent) child;

            if (compositeChild.getParent() != null && compositeChild.getParent() instanceof CompositeComponent) {
                ((CompositeComponent) compositeChild.getParent()).removeChild(compositeChild);
            }

            compositeChild.setParent(this);
        } else if (child instanceof Card) {
            Card cardChild = (Card) child;

            if(cardChild.getParent() != null && cardChild.getParent() instanceof CompositeComponent) {
                ((CompositeComponent) cardChild.getParent()).removeChild(cardChild);
            }
            cardChild.setParent(this);
        }
        children.add(child);
    }

    public void removeChild(Component child){
        children.remove(child);
        if(child instanceof CompositeComponent) {
           ((CompositeComponent) child).setParent(null);
        } else if (child instanceof Card) {
            ((Card) child).setParent(null);
        }
    }

    public void delete(){


        List<Component> childCopy = new ArrayList<>(children);

        for(Component child: childCopy) {
            child.delete();
        }

        if(parent != null && parent instanceof CompositeComponent) {
            ((CompositeComponent) parent).removeChild(this);
        }
        System.out.println("Deleted: "+this.name);
    }

    public void ls(ComponentVisitor visitor){

        for(Component child: children){
            child.ls(visitor);
        }
    }

}
