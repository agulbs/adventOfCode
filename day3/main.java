import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        char[][] grid = new char[323][31];

        String line;
        int idx =0;
        while((line = reader.readLine()) != null){
            for(int i=0; i<line.length(); i++){
                grid[idx][i] = line.charAt(i);
            }
            idx++;
        }

        int ans = 1;
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        for(int i =0; i<slopes.length; i++){
            ans *= traverse(grid, slopes[i][0], slopes[i][1]);
        }

        System.out.println(ans);



    }

    static int traverse(char[][] grid, int slopeX, int slopeY){
        int x = 0, y = 0, ans = 0;

        while(y<grid.length){
            if(grid[y][x] == '#'){
                ans++;
            }

            x = (x + slopeX) % 31;
            y += slopeY;

        }

        return ans;
    }





}
