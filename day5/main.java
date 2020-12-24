import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<Integer> list = new ArrayList<Integer>();


        String line;
        int ans = 0;
        while((line = reader.readLine()) != null){

            int i = highestSeat(line);
            list.add(i);
            if(i > ans)
                ans = i;
        }

        System.out.println(ans);

        Collections.sort(list);
        System.out.println(missingSeat(list));




    }

    static int highestSeat(String seating){
        int[] seatingChart = {0, 127, 0, 7};
        for(int i=0; i < seating.length(); i++){
            char c = seating.charAt(i);
            switch (c){
                case 'F':
                    seatingChart[1] = (seatingChart[0] + seatingChart[1]) / 2;
                    break;
                case 'B':
                    seatingChart[0] = (seatingChart[0] + seatingChart[1]) / 2;
                    seatingChart[0]++;
                    break;
                case 'L':
                    seatingChart[3] = (seatingChart[2] + seatingChart[3]) / 2;
                    break;
                case 'R':
                    seatingChart[2] = (seatingChart[2] + seatingChart[3]) / 2;
                    seatingChart[2]++;
                    break;
            }
        }

        return seatingChart[0] * 8 + seatingChart[2];
    }


    static int missingSeat(ArrayList<Integer> seats){
        int missing = seats.get(0);

        for(int i =0; i <seats.size(); i++){
            if(seats.get(i) != missing)
                return missing;

            missing++;
        }

        return 0;
    }
}
