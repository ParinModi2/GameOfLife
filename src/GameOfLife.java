
public class GameOfLife {

    private static final int WIDTH = 25;
    private static final int HEIGHT = 25;
    private static final int GENERATIONS = 20;
    
    private static final int ALIVE = 1;
    private static final int DEAD = 0;

    public static void main(String[] args) throws InterruptedException {

      //Create initial universe with a glider pattern
        int[][] universe = new int[HEIGHT][WIDTH];

        // Place glider in the center
        placeGliderInCenter(universe, WIDTH / 2, HEIGHT / 2);

        for (int gen = 0; gen < GENERATIONS; gen++) {
            printUniverse(universe, gen);
            universe = nextGeneration(universe);
            Thread.sleep(300); // pause so output is readable
        }
    }

    private static void placeGliderInCenter(int[][] grid, int centerX, int centerY) {

        // Glider pattern
        int[][] glider = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1}
        };

        //Placing it in center of the grid
        for (int i = 0; i < glider.length; i++) {
            for (int j = 0; j < glider[0].length; j++) {
                grid[centerY + i][centerX + j] = glider[i][j];
            }
        }
    }

    private static int countLiveNeighbors(int[][] grid, int x, int y) {

        int totalNumberOfNeighbors = 0;

        // left, right, up, down and diagonals will become  x + (-1,1) and y + (-1,1)
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                
                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT) {
                    totalNumberOfNeighbors += grid[ny][nx];
                }
            }
        }
        return totalNumberOfNeighbors;
    }

    private static int[][] nextGeneration(int[][] grid) {
    	
        int[][] newGrid = new int[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int totalNumberOfNeighbors = countLiveNeighbors(grid, x, y);

                if (grid[y][x] == ALIVE) {
                    if (totalNumberOfNeighbors == 2 || totalNumberOfNeighbors == 3) {
                        newGrid[y][x] = ALIVE;
                    } else {
                        newGrid[y][x] = DEAD;
                    }
                } else {
                    if (totalNumberOfNeighbors == 3) {
                        newGrid[y][x] = ALIVE;
                    }
                }
            }
        }
        return newGrid;
    }



    private static void printUniverse(int[][] grid, int generation) {

        System.out.println("Generation " + generation);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                System.out.print(grid[y][x] == ALIVE ? "X" : " ");
            }
            System.out.println();
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }
}