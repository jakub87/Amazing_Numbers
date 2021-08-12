package numbers.model;

public enum Properties {
    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE, JUMPING, SAD, HAPPY;

    public static Properties getProperties(String property) {
        if (property.charAt(0) == '-') {
            property = property.substring(1);
        }
        for (Properties properties : Properties.values()) {
            if (properties.name().equalsIgnoreCase(property))
                return properties;
        }
        return null;
    }
}
