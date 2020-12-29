import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<Long> list = new ArrayList<Long>();
        long ans = 0;

        String line;
        int num = 0;
        while((line = reader.readLine()) != null){
            list.add(Long.parseLong(line));
        }

        long key = getKey(list);
        long weakness = findWeakness(list, key);
        System.out.printf("key: %d\tweakness: %d\n", key, weakness);
    }

    static long getKey(ArrayList<Long> list){
        for(int i=0; i <list.size(); i++){
            boolean valid = false;

            for (int j = i; j < i + 25; j++){
                for (int k = i; k < i + 25; k++){
                    if (j == k) { continue; }

                    if (list.get(j) + list.get(k) == list.get(i+ 25)) {
                        valid = true;
                        j = i + 25;
                        break;
                    }
                }
            }

            if (!valid) { return list.get(i+ 25); }
        }

        return 0;
    }

    static long findWeakness(ArrayList<Long> list, long key){
        for (int i = 0; i < list.size(); i++) {
            long sum = 0;
            for (int j = i; j < list.size(); j++) {
                sum += list.get(j);
                if (sum > key) { break; }

                if (sum == key) {
                    long min = Long.MAX_VALUE;
                    long max = Long.MIN_VALUE;
                    for (int k = i; k <= j; k++) {
                        min = Long.min(min, list.get(k));
                        max = Long.max(max, list.get(k));
                    }

                    return min + max;
                }
            }
        }

        return 0;
    }


}
