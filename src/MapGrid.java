/**
 * Created by erivan on 13/07/2017.
 */
public class MapGrid {
    private int area = 0;
    private int perimeter = 0;
    private int islandCount = 0;
    private int[][] grid;

    public MapGrid(int[][] grid) {
        this.grid = grid;
        this.core();
    }

    private void core() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.calcArea(i, j, 0);
                this.calcPerimeter(i, j, 0);
                this.countIsland(i, j, 1);
            }
        }
    }

    /**
     * Count the number of nodes grater than
     * the sea level.
     */
    private void calcArea(int row, int col, int seaLevel) {
        if (this.grid[row][col] > seaLevel)
            this.area++;
    }

    /**
     * Checks if the node is located along the
     * perimeter of an island and its position
     * over the perimeter, and uses that information
     * to calculate the perimeter value.
     */
    private void calcPerimeter(int row, int col, int seaLevel) {
        //horizontal
        /*
        * If the left or right border of the node is on one of the four
        * corners of the grid, and add it to the perimeter.
        */
        if (this.grid[row][col] > seaLevel && (col == 0 || col == this.grid[row].length - 1))
            this.perimeter++;

        /*
        * Checks if the left border of the node is on the island perimeter.
        */
        if (col > 0 && (this.grid[row][col] > seaLevel && this.grid[row][col - 1] <= seaLevel))
            this.perimeter++;

        /*
        * Checks if the right border of the node is on the island perimeter.
        * It happens when the node change from land to sea level node.
        */
        if (col > 0 && col <= this.grid[row].length - 1 && (this.grid[row][col] <= seaLevel && this.grid[row][col - 1] > seaLevel))
            this.perimeter++;

        //vertical
        /*
        * If the top or bottom border of the node is on one of the four
        * corners of the grid, and add it to the perimeter.
        */
        if (this.grid[row][col] > seaLevel && (row == 0 || row == this.grid.length - 1))
            this.perimeter++;

        /*
        * Checks if the top border of the node is on the island perimeter.
        */
        if (row > 0 && (this.grid[row][col] > seaLevel && this.grid[row - 1][col] <= seaLevel))
            this.perimeter++;

        /*
        * Checks if the bottom border of the node is on the island perimeter.
        */
        if (row > 0 && row < this.grid.length - 1 && (this.grid[row][col] > seaLevel && this.grid[row + 1][col] <= seaLevel))
            this.perimeter++;
    }

    /**
     * Find the groups of connected land nodes on the grid
     */
    private void countIsland(int row, int col, int seaLevel) {
        /*
        * Just for nodes over the sea level.
        * If the left, top and right nodes of the current node are
        * at the sea level, so this node is the first node of an island, else,
        * if just the left node of the current node is over the sea level,
        * checks if all of the next nodes at this line, haven't a top node
        * over the sea level.
        */
        if (this.grid[row][col] > seaLevel) {
            if ((col == 0 || this.grid[row][col - 1] <= seaLevel) &&
                    (row == 0 || this.grid[row - 1][col] <= seaLevel) &&
                    (col == this.grid[row].length - 1 || this.grid[row][col + 1] <= seaLevel)) {
                this.islandCount++;
            } else if ((col == 0 || this.grid[row][col - 1] <= seaLevel) &&
                    (row == 0 || this.grid[row - 1][col] <= seaLevel)) {
                int i = 1;
                while(col+i < grid[row].length){
                    if((this.grid[row][col + i] > seaLevel && row == 0 || this.grid[row - 1][col + i] > seaLevel))
                        return;
                    i++;
                }
                this.islandCount++;
            }
        }
    }

    /**
     * Print the map showing the land
     */
    public void printMap(int seaLevel){
        System.out.println();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                System.out.printf(this.grid[i][j] <= seaLevel?" %d ":"[%d]", this.grid[i][j]);
            }
            System.out.println();
        }
    }

    public int getArea() {
        return area;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public int getIslandCount() {
        return islandCount;
    }
}