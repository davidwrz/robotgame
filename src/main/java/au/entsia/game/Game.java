package au.entsia.game;

import au.entsia.robot.Robot;
import au.entsia.util.InputReader;

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
            switch (inputReader.getInput()) {
//                case "PLACE" -> placeRobot(horizontalCoordinate, verticalCoordinate, facing);
                case "MOVE" -> moveRobot();
                case "RIGHT" -> rotateRobot(RotateOrder.RIGHT);
                case "LEFT" -> rotateRobot(RotateOrder.LEFT);
                case "REPORT" -> printOutput();
                case "EXIT" -> exitGame();
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
        if (PositionValidator.isRobotPositionValid(robot.getX(), robot.getY(), HORIZONTAL_SIZE, VERTICAL_SIZE))
            robot.move();
    }

    private void printOutput() {
        String position = robot.getPosition();
        System.out.println("Output: " + position);
    }

    private void placeRobot(int x, int y, String facing) {
        robot = new Robot(x, y, facing);
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
        return PositionValidator.isRobotPositionValid(x, y, HORIZONTAL_SIZE, VERTICAL_SIZE);
    }

    private String[] getCoordinates(String place) {
        return place
                .split(" ")[1]
                .split(",");
    }
}
