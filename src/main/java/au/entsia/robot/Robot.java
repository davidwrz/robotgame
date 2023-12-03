package au.entsia.robot;

public class Robot {
    private int x;
    private int y;
    private Facing facing;

    public Robot(int x, int y, String facing) {
        this.x = x;
        this.y = y;
        this.facing = Facing.valueOf(facing);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getFacing() {
        return facing.toString();
    }

    public void rotateRight() {
        facing = facing.turnRight();
        System.out.println("Robot rotated right");
    }

    public void rotateLeft() {
        facing = facing.turnLeft();
        System.out.println("Robot rotated left");

    }

    public void move() {
        switch (facing) {
            case NORTH -> y++;
            case SOUTH -> y--;
            case EAST -> x++;
            case WEST -> x--;
        }
        System.out.println("Robot moved " + facing);
    }

    public void place(int x, int y, String facing) {
        this.x = x;
        this.y = y;
        this.facing = Facing.valueOf(facing);
    }
}
