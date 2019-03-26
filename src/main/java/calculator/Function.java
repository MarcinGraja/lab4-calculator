package calculator;

public class Function {
    private String displayedName;
    private String name;

    Function(String displayedName, String name) {
        this.displayedName = displayedName;
        this.name = name;

    }

    @Override
    public String toString() {
        return displayedName;
    }

    String getName() {
        return name;
    }
}
