package au.entsia.game;

import au.entsia.robot.Robot;
import au.entsia.util.InputReader;

import static au.entsia.game.PositionValidator.isRobotPositionValid;

public class Game {

    private final InputReader inputReader = new InputReader();
    private final int VERTICAL_SIZE = 5;
    private final int HORIZONTAL_SIZE = 5;
    private static final String PLACE = "PLACE";
    private Robot robot;

    public Game() {
        play();
    }

    public void play() {
        startByFirstCommand();

        while (true) {
            String command = inputReader.getInput();
            if (command.startsWith("PLACE ")) {
                placeRobot(command);
            } else {
                switch (command) {
                    case "MOVE" -> moveRobot();
                    case "RIGHT" -> rotateRobot(RotateOrder.RIGHT);
                    case "LEFT" -> rotateRobot(RotateOrder.LEFT);
                    case "REPORT" -> printOutput();
                    case "EXIT" -> exitGame();
                }
            }
        }
    }

    private void exitGame() {
        inputReader.close();
        System.exit(0);
    }

    private void rotateRobot(RotateOrder direction) {
        if (direction.equals(RotateOrder.RIGHT)) {
            robot.rotateRight();
        } else {
            robot.rotateLeft();
        }
    }

    private void moveRobot() {
        int x = robot.getX();
        int y = robot.getY();
        String facing = robot.getFacing();
        switch (facing) {
            case "NORTH" -> y++;
            case "SOUTH" -> y--;
            case "EAST" -> x++;
            case "WEST" -> x--;
        }
        if (isRobotPositionValid(x, y, HORIZONTAL_SIZE, VERTICAL_SIZE))
            robot.move();
    }

    private void printOutput() {
        String result = String.format("%d,%d,%s", robot.getX(), robot.getY(), robot.getFacing());
        System.out.println("Output: " + result);
    }

    private void placeRobot(String input) {
        String[] coordinates = getCoordinates(input);
        int horizontalCoordinate = Integer.parseInt(coordinates[0]);
        int verticalCoordinate = Integer.parseInt(coordinates[1]);
        String facing = coordinates[2];
        robot.place(horizontalCoordinate, verticalCoordinate, facing);
    }

    private void startByFirstCommand() {
        String input;
        while (true) {
            input = inputReader.getInput();
            if (input.contains(PLACE)) {
                String[] coordinates = getCoordinates(input);
                int horizontalCoordinate = Integer.parseInt(coordinates[0]);
                int verticalCoordinate = Integer.parseInt(coordinates[1]);
                String facing = coordinates[2];
                if (isPositionValid(horizontalCoordinate, verticalCoordinate)) {
                    createRobot(horizontalCoordinate, verticalCoordinate, facing);
                    System.out.println("Robot initialized");
                    return;
                }
            }
        }
    }

    private void createRobot(int x, int y, String facing) {
        robot = new Robot(x, y, facing);
    }

    private boolean isPositionValid(int x, int y) {
        return isRobotPositionValid(x, y, HORIZONTAL_SIZE, VERTICAL_SIZE);
    }

    private String[] getCoordinates(String place) {
        return place
                .split(" ")[1]
                .split(",");
    }
}
