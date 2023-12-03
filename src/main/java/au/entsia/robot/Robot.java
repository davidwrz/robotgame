package au.entsia.robot;

public class Robot {
    private int horizontalCoordinate;
    private int verticalCoordinate;
    private Facing facing;

    public int getHorizontalCoordinate() {
        return horizontalCoordinate;
    }

    public int getVerticalCoordinate() {
        return verticalCoordinate;
    }

    public String getFacing() {
        return facing.toString();
    }

    public void rotateRight() {
        facing = facing.turnRight();
    }

    public void rotateLeft() {
        facing = facing.turnLeft();
    }

    public void move() {
        switch (facing) {
            case NORTH -> verticalCoordinate++;
            case SOUTH -> verticalCoordinate--;
            case EAST -> horizontalCoordinate++;
            case WEST -> horizontalCoordinate--;
        }
    }

    public void place(int horizontalCoordinate, int verticalCoordinate, String facing) {
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
        this.facing = Facing.valueOf(facing);
    }
}
