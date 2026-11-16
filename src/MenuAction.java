public abstract class MenuAction {
    private String name;

    public MenuAction(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute();
}
