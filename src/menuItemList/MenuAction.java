package menuItemList;

public abstract class MenuAction {
    private String name;

    public MenuAction(String name) {

        this.name=name;
    }

    public String getName() { // for future

        return name;
    }

    public abstract void execute();
}
