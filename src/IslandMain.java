import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by erivan on 12/07/2017.
 */
public class IslandMain {
    public static void main(String... args){
        Date d = new Date();
        ArrayList<String> linhas = null;
        int [][] matrix;
        try{
            linhas = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("./map"));
            while(br.ready()){
                linhas.add(br.readLine());
            }
            br.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        matrix = new int[linhas.size()][];
        for (int i = 0; i < matrix.length; i++){
            String l[] = linhas.get(i).trim().split(" ");
            matrix[i] = new int[l.length];
            for (int j = 0; j < l.length; j++) {
                matrix[i][j] = l[j] != null ? Integer.valueOf(l[j]): 0;
            }
        }
        printMatrix(matrix);

    }

    private static void printMatrix(int[][] matrix){
        int area = 0;
        int perimetro = 0;
        int seaLevel = 0;

        for (int i = 0; i< matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //calculo area
                if(matrix[i][j] > seaLevel)
                    area++;

                //calculo perimetro
                //horizontal
               if(matrix[i][j] > seaLevel && (j==0 || j==matrix[i].length-1))
                   perimetro++;

               if(j>0 && (matrix[i][j] > seaLevel && matrix[i][j-1] <= seaLevel)) //borda esquerda
                    perimetro++;

               if(j>0 && j<=matrix[i].length-1 && (matrix[i][j] <= seaLevel && matrix[i][j-1] > seaLevel)) //borda direita
                   perimetro++;

               //vertical
               if(matrix[i][j] > seaLevel && (i==0 || i==matrix.length-1))
                   perimetro++;

                if(i>0 && (matrix[i][j] > seaLevel && matrix[i-1][j] <= seaLevel)) //borda superior
                    perimetro++;

                if(i>0 && i<matrix.length-1 && (matrix[i][j] > seaLevel && matrix[i+1][j] <= seaLevel)) //borda inferior
                    perimetro++;


                System.out.printf(matrix[i][j] <= seaLevel?" %d ":"[%d]", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.printf("area: %d\n", area);
        System.out.printf("perimetro: %d\n", perimetro);
    }
}
