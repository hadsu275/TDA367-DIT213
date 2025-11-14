
import java.util.*;

public class MazeGenerator {
    private final int width;
    private final int height;
    final Cell[][] maze;
    private final Random random = new Random();

    private static final int[][] DIRS = {
            {-1, 0, 0, 1}, // N
            {1, 0, 1, 0},  // S
            {0, 1, 2, 3},  // E
            {0, -1, 3, 2}  // W
    };
    private final double densityFactor;
    public MazeGenerator(int width, int height, double densityFactor) {
        this.width = width;
        this.height = height;
        this.densityFactor = densityFactor;
        this.maze = new Cell[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                maze[y][x] = new Cell();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Cell getCell(int y, int x) { return maze[y][x]; }

    private void openBorders() {
        // Toppen
        for (int x = 0; x < width; x++)
            maze[0][x].walls[0] = false;     // N

        // Botten
        for (int x = 0; x < width; x++)
            maze[height - 1][x].walls[1] = false; // S

        // Vänster
        for (int y = 0; y < height; y++)
            maze[y][0].walls[3] = false;     // W

        // Höger
        for (int y = 0; y < height; y++)
            maze[y][width - 1].walls[2] = false; // E
    }


    public void generate() {
        Stack<int[]> stack = new Stack<>();
        int startY = random.nextInt(height);
        int startX = random.nextInt(width);
        maze[startY][startX].visited = true;
        stack.push(new int[]{startY, startX});

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int y = current[0], x = current[1];
            List<int[]> neighbors = new ArrayList<>();

            for (int[] dir : DIRS) {
                int ny = y + dir[0], nx = x + dir[1];
                if (ny >= 0 && ny < height && nx >= 0 && nx < width && !maze[ny][nx].visited) {
                    neighbors.add(new int[]{ny, nx, dir[2], dir[3]});
                }
            }

            if (!neighbors.isEmpty()) {
                int[] chosen = neighbors.get(random.nextInt(neighbors.size()));
                int ny = chosen[0], nx = chosen[1], wallCurrent = chosen[2], wallNeighbor = chosen[3];
                if (random.nextDouble() < densityFactor) {
                    maze[y][x].walls[wallCurrent] = false;
                    maze[ny][nx].walls[wallNeighbor] = false;
                }
                maze[ny][nx].visited = true;
                stack.push(new int[]{ny, nx});
            } else {
                stack.pop();
            }
        }
    }

    public static class Cell {
        boolean[] walls = {true, true, true, true}; // N, S, E, W
        boolean visited = false;
    }
}
