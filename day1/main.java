import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        HashSet<Integer> set = new HashSet<Integer>();

        String line;
        while((line = reader.readLine()) != null){
            int n = Integer.parseInt(line);
            set.add(n);
        }

        int ans;
        ans = main.partOne(set);
        ans = main.partTwo(set);

        System.out.println(ans);
    }

    static int partOne(HashSet<Integer> set){
        for(Integer i: set){
            if(set.contains(2020 - i)){
                return i * (2020-i);
            }
        }

        return 0;
    }

    static int partTwo(HashSet<Integer> set){
        for(Integer i: set){
            for(Integer j: set){
                int num = 2020 - i - j;
                if(set.contains(num)){
                    return num * i * j;
                }
            }
        }

        return 0;
    }
}
