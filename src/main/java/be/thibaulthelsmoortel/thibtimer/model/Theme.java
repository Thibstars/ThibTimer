package be.thibaulthelsmoortel.thibtimer.model;

/**
 * Enum containing available themes.
 *
 * @author Thibault Helsmoortel
 */
public enum Theme {
    DARK("Dark"), LIGHT("Light");

    private final String name;

    Theme(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
