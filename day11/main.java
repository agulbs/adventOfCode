import java.util.*;
import java.io.*;

public class main {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> list = new ArrayList<String>();

        String line;
        int num = 0;
        while((line = reader.readLine()) != null){
            list.add(line);
        }

        char[][] seating = setupSeatingGrid(list);
        while(true){
            char[][] seats = getNextSeats(seating);

            if(seatingSame(seating, seats)){
                num = countSeats(seating, '#');
                break;
            }

            seating = seats;
        }

        System.out.println(num);

        seating = setupSeatingGrid(list);
        num=0;

        while(true){
            char[][] seats = getNextSeatsVisibility(seating);

            if(seatingSame(seating, seats)){
                num = countSeats(seating, '#');
                break;
            }

            seating = seats;
        }

        System.out.println(num);
    }


    static char[][] setupSeatingGrid(ArrayList<String> seating){
        int rows = seating.size();
        int cols = seating.get(0).length();
        char[][] chart = new char[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                chart[i][j] = seating.get(i).charAt(j);
            }
        }

        return chart;
    }

    static char[][] getNextSeats(char[][] seating){
        int rows = seating.length;
        int cols = seating[0].length;

        char[][] nextSeat = new char[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(seating[i][j] == 'L' && adjacentSeats(seating, i, j) == 0){
                    nextSeat[i][j] = '#';
                }
                else if (seating[i][j] == '#' && adjacentSeats(seating, i, j) >= 4){
                    nextSeat[i][j] = 'L';
                }
                else{
                    nextSeat[i][j] = seating[i][j];
                }
            }
        }

        return nextSeat;
    }

    static char[][] getNextSeatsVisibility(char[][] seating){
        int rows = seating.length;
        int cols = seating[0].length;

        char[][] nextSeat = new char[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(seating[i][j] == 'L' && visibleSeats(seating, i, j) == 0){
                    nextSeat[i][j] = '#';
                }
                else if (seating[i][j] == '#' && visibleSeats(seating, i, j) >= 5){
                    nextSeat[i][j] = 'L';
                }
                else{
                    nextSeat[i][j] = seating[i][j];
                }
            }
        }

        return nextSeat;
    }

    static int[][] offsets = new int[][] { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };

    static int adjacentSeats(char[][] seating, int row, int col){
        int cnt = 0;
        for(int[] offset: offsets){
            int r = row + offset[0];
            int c = col + offset[1];

            if(r < 0 || r >= seating.length || c < 0 || c >= seating[r].length){
                continue;
            }

            if(seating[r][c] == '#')
                cnt++;
        }

        return cnt;
    }


    static int visibleSeats(char[][] seating, int row, int col){
        int cnt = 0;
        for(int[] offset: offsets){
            int r = row;
            int c = col;

            while(true){
                r += offset[0];
                c += offset[1];

                if(r < 0 || r >= seating.length || c < 0 || c >= seating[r].length){
                    break;
                }

                if(seating[r][c] == 'L'){
                    break;
                }

                if(seating[r][c] == '#'){
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }

    static int countSeats(char[][] seating, char flag){
        int cnt = 0;
        for(int i=0; i < seating.length; i++){
            for(int j=0; j<seating[i].length; j++){
                if(seating[i][j] == flag)
                    cnt++;
            }
        }

        return cnt;
    }

    static boolean seatingSame(char[][] a, char[][] b){
        if(a.length != b.length || a[0].length != b[0].length){
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void seatingToString(char[][] seating){
        System.out.printf("%d %d\n", seating.length, seating[0].length);
        for(int i=0; i<seating.length; i++){
            for(int j=0; j<seating[i].length; j++){
                System.out.print(seating[i][j]);
            }
            System.out.printf("\n");
        }
    }

}
