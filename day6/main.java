import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> answers = new ArrayList<String>();

        String line;
        int ans = 0;
        while((line = reader.readLine()) != null){
            if(line.length() == 0){
                // ans += countAnswers(answers);
                ans += countYes(answers);
                answers.clear();
            } else {
                answers.add(line);
            }
        }

        System.out.println(ans);
    }

    static int countAnswers(ArrayList<String> answers){
        Set<Character> set = new HashSet<Character>();

        for(String s: answers){
            for(int i=0; i < s.length(); i++){
                set.add(s.charAt(i));
            }
        }

        return set.size();
    }

    static int countYes(ArrayList<String> answers){
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for(String s: answers){
            for(int i=0; i < s.length(); i++){
                char c = s.charAt(i);
                if(map.containsKey(c))
                    map.put(c, map.get(c)+1);
                else
                    map.put(c, 1);
            }
        }

        int ans = 0;
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() == answers.size())
                ans++;
        }

        return ans;
    }


}
