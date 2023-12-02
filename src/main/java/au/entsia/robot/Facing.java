package au.entsia.robot;

enum Facing {
    NORTH, EAST, SOUTH, WEST;

    private static final Facing[] VALUES = values();

    Facing turnRight() {
        return VALUES[(ordinal() + 1) % VALUES.length];
    }

    Facing turnLeft() {
        return VALUES[(ordinal() - 1 + VALUES.length) % VALUES.length];
    }
}
