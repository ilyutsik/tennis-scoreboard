package game.enums;

public enum Point {

    zero("0"),
    fifteen("15"),
    thirty("30"),
    forty("40"),
    more("more"),
    less("less"),
    equal("equal"),
    game("game");

    private final String value;

    Point(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}