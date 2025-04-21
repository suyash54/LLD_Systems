package Trello;

public interface Component {
    public int getId();
    public String getName();
    public void delete();
    public Component getParent();
    public void ls(ComponentVisitor visitor);
}
