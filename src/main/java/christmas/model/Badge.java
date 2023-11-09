package christmas.model;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

    private final String name;
    private final int price;

    Badge(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadge(final int price) {
        if (price >= 20000) {
            return SANTA;
        }
        if (price >= 10000) {
            return TREE;
        }
        if (price >= 5000) {
            return STAR;
        }
        return NONE;
    }
}
