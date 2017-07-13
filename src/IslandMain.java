import java.io.*;
import java.util.*;
import java.util.Date;

/**
 * Created by erivan on 12/07/2017.
 */
public class IslandMain {
    public static void main(String[] args){
        long d = new Date().getTime();
        List<String> lines = null;
        int [][] grid;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./map"));
            lines = new ArrayList<String>();
            while(reader.ready()){
                lines.add(reader.readLine());
            }
            reader.close();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        /*
        * Creating and fill the grid
        */
        grid = new int[lines.size()][];
        for (int i = 0; i < grid.length; i++){
            String l[] = lines.get(i).trim().split(" ");
            grid[i] = new int[l.length];
            for (int j = 0; j < l.length; j++) {
                grid[i][j] = l[j] != null ? Integer.valueOf(l[j]): 0;
            }
        }

        MapGrid map  = new MapGrid(grid);
        System.out.printf("Out #1 (Total Area): %d\n", map.getArea());
        System.out.printf("Out #2 (Perimeter): %d\n", map.getPerimeter());
        System.out.printf("Out #3 (Island Count): %d\n", map.getIslandCount());

        map.printMap(0);

        System.out.printf("\nExecution time: %dms ", (new Date().getTime() - d));
    }
}