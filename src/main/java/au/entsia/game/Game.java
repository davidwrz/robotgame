package au.entsia.game;

import au.entsia.input.Command;
import au.entsia.input.CommandService;
import au.entsia.input.InputReader;
import au.entsia.robot.Robot;

import java.util.logging.Logger;

import static au.entsia.game.PositionValidator.isRobotPositionValid;
import static au.entsia.input.Command.PLACE;

public class Game {
    private final InputReader inputReader;
    private final Logger logger;
    private final Robot robot;
    private boolean gameRunning = true;

    public Game(Robot robot, InputReader inputReader) {
        this.robot = robot;
        this.inputReader = inputReader;
        logger = Logger.getLogger(Game.class.getName());
    }

    public void play() {
        triggerPlacingRobot();

        while (gameRunning) {
            String input = inputReader.getInput();
            Command command = CommandService.getCommand(input);
            switch (command) {
                case PLACE -> placeRobot(input);
                case MOVE -> moveRobot();
                case RIGHT -> rotateRobot(RotateOrder.RIGHT);
                case LEFT -> rotateRobot(RotateOrder.LEFT);
                case REPORT -> printOutput();
                case EXIT -> exitGame();
                case UNKNOWN -> logger.info(String.format("Unknown command: %s", input));
            }
        }
    }

    private void triggerPlacingRobot() {
        String input;
        while (true) {
            input = inputReader.getInput();
            if (input.startsWith(PLACE.toString())) {
                placeRobot(input);
                return;
            }
        }
    }

    void placeRobot(String input) {
        String[] coordinates = input.split(" ")[1].split(",");
        int horizontalCoordinate = Integer.parseInt(coordinates[0]);
        int verticalCoordinate = Integer.parseInt(coordinates[1]);
        String facing = coordinates[2];
        if (isRobotPositionValid(horizontalCoordinate, verticalCoordinate)) {
            robot.place(horizontalCoordinate, verticalCoordinate, facing);
            logger.info(String.format("Robot placed: %d,%d,%s",
                    robot.getHorizontalCoordinate(),
                    robot.getVerticalCoordinate(),
                    robot.getFacing()));
        }
    }

    void moveRobot() {
        int horizontalCoordinate = robot.getHorizontalCoordinate();
        int verticalCoordinate = robot.getVerticalCoordinate();
        String facing = robot.getFacing();
        switch (facing) {
            case "NORTH" -> verticalCoordinate++;
            case "SOUTH" -> verticalCoordinate--;
            case "EAST" -> horizontalCoordinate++;
            case "WEST" -> horizontalCoordinate--;
        }
        if (isRobotPositionValid(horizontalCoordinate, verticalCoordinate)) {
            robot.move();
            logger.info(String.format("Robot moved: %s", facing));
        }
    }

    void rotateRobot(RotateOrder direction) {
        if (direction.equals(RotateOrder.RIGHT)) {
            robot.rotateRight();
        } else {
            robot.rotateLeft();
        }
        logger.info(String.format("Robot rotated: %s", direction));
    }

    String printOutput() {
        String result = String.format("Output: %d,%d,%s",
                robot.getHorizontalCoordinate(),
                robot.getVerticalCoordinate(),
                robot.getFacing());
        System.out.println(result);
        return result;
    }

    void exitGame() {
        gameRunning = false;
        inputReader.close();
        logger.info("End of the game");
    }
}
