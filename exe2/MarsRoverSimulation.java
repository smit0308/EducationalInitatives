import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Command {
    void execute();
}

class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.move();
    }
}

class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnRight();
    }
}

interface Direction {
    void move(Rover rover);
    Direction turnLeft();
    Direction turnRight();
}

class North implements Direction {
    @Override
    public void move(Rover rover) {
        rover.setPosition(rover.getX(), rover.getY() + 1);
    }

    @Override
    public Direction turnLeft() {
        return new West();
    }

    @Override
    public Direction turnRight() {
        return new East();
    }

    @Override
    public String toString() {
        return "N";
    }
}

class East implements Direction {
    @Override
    public void move(Rover rover) {
        rover.setPosition(rover.getX() + 1, rover.getY());
    }

    @Override
    public Direction turnLeft() {
        return new North();
    }

    @Override
    public Direction turnRight() {
        return new South();
    }

    @Override
    public String toString() {
        return "E";
    }
}

class South implements Direction {
    @Override
    public void move(Rover rover) {
        rover.setPosition(rover.getX(), rover.getY() - 1);
    }

    @Override
    public Direction turnLeft() {
        return new East();
    }

    @Override
    public Direction turnRight() {
        return new West();
    }

    @Override
    public String toString() {
        return "S";
    }
}

class West implements Direction {
    @Override
    public void move(Rover rover) {
        rover.setPosition(rover.getX() - 1, rover.getY());
    }

    @Override
    public Direction turnLeft() {
        return new South();
    }

    @Override
    public Direction turnRight() {
        return new North();
    }

    @Override
    public String toString() {
        return "W";
    }
}

class Rover {
    private int x, y;
    private Direction direction;
    private Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
        int newX = x;
        int newY = y;
        direction.move(this);
        
        if (grid.hasObstacle(x, y) || !grid.isWithinBounds(x, y)) {
            x = newX;
            y = newY;
            System.out.println("Obstacle or out of bounds detected, cannot move forward.");
        } else {
            System.out.println("Moved to (" + x + ", " + y + ")");
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();
        System.out.println("Turned left, now facing " + direction);
    }

    public void turnRight() {
        direction = direction.turnRight();
        System.out.println("Turned right, now facing " + direction);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void statusReport() {
        System.out.println("Rover is at (" + x + ", " + y + ") facing " + direction + ".");
    }
}

class Grid {
    private int width, height;
    private List<int[]> obstacles = new ArrayList<>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addObstacle(int x, int y) {
        obstacles.add(new int[]{x, y});
    }

    public boolean hasObstacle(int x, int y) {
        for (int[] obstacle : obstacles) {
            if (obstacle[0] == x && obstacle[1] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}

public class MarsRoverSimulation {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid.addObstacle(2, 2);
        grid.addObstacle(3, 5);

        Rover rover = new Rover(0, 0, new North(), grid);

        Command moveCommand = new MoveCommand(rover);
        Command turnLeftCommand = new TurnLeftCommand(rover);
        Command turnRightCommand = new TurnRightCommand(rover);

        List<Command> commands = new ArrayList<>();
        commands.add(moveCommand);  
        commands.add(moveCommand);  
        commands.add(turnRightCommand);  
        commands.add(moveCommand);  
        commands.add(turnLeftCommand);  
        commands.add(moveCommand);  
        for (Command command : commands) {
            command.execute();
        }

        rover.statusReport();
    }
}
