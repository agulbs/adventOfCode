import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String line;
        int ans = 0;
        while((line = reader.readLine()) != null){
            if(validatePassword(line))
                ans+=1;
            else
                System.out.println(line + "\n");
        }

        System.out.println(ans);

    }

    static boolean parsePassword(String text){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String[] tokens = text.split(" ");
        String[] bounds = tokens[0].split("-");

        for(int i =0; i < tokens[2].length(); i++){
            char c = tokens[2].charAt(i);
            if (map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        if(map.containsKey(tokens[1].charAt(0))){
            int low = Integer.parseInt(bounds[0]);
            int high = Integer.parseInt(bounds[1]);

            if(map.get(tokens[1].charAt(0)) >= low && map.get(tokens[1].charAt(0)) <= high)
                return true;
        }

        return false;

    }

    static boolean validatePassword(String text){
        String[] tokens = text.split(" ");
        String[] bounds = tokens[0].split("-");
        char c = tokens[1].charAt(0);
        String password = tokens[2];

        int low = Integer.parseInt(bounds[0]);
        int high = Integer.parseInt(bounds[1]);

        if(low != 0)
            low--;
        if(high != 0)
            high--;

        if((password.charAt(low)==c && password.charAt(high)==c) || (password.charAt(low)!=c && password.charAt(high)!=c))
            return false;

        return true;
    }



}
