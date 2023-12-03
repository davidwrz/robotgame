package au.entsia.robot;

enum Facing {
    NORTH, EAST, SOUTH, WEST;

    Facing turnRight() {
        return values()[(ordinal() + 1) % values().length];
    }

    Facing turnLeft() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }
}
