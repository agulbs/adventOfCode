import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<Integer> list = new ArrayList<Integer>();

        String line;
        int num = 0;
        while((line = reader.readLine()) != null){
            list.add(Integer.parseInt(line));
        }

        Collections.sort(list);

        System.out.println(findChain(list));
        System.out.println(findDistinctChains(list));

    }


    static int findChain(ArrayList<Integer> list){
        int start = 0, end = 1;
        for(int i=0; i<list.size(); i++){
            int previous;
            if(i==0)
                previous = 0;
            else
                previous = list.get(i-1);

            int p = list.get(i) - previous;

            if(p == 1)
                start++;
            else if ( p == 3)
                end++;
        }

        return start * end;
    }

    static long findDistinctChains(ArrayList<Integer> list){
        long[] sum = new long[list.get(list.size()-1) + 1];
        sum[0] = 1;
        for(int i=0; i<list.size(); i++){
            long[] combos = {0, 0, 0};

            if(list.get(i) >= 3)
                combos[0] = sum[list.get(i) - 3];
            if(list.get(i) >= 2)
                combos[1] = sum[list.get(i) - 2];
            if(list.get(i) >= 1)
                combos[2] = sum[list.get(i) - 1];

            sum[list.get(i)] = combos[0] + combos[1] + combos[2];

        }

        return sum[sum.length - 1];

    }

}
