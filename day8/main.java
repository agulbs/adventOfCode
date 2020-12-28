import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String[]> list = new ArrayList<String[]>();

        String line;
        int num = 0;
        while((line = reader.readLine()) != null){
            String[] opers = {line.substring(0,3), line.substring(4)};
            list.add(opers);
        }

        System.out.println(getAccum(list));
        System.out.println(findFault(list));

    }

    static int oper(String token){
        char sign = token.charAt(0);
        int oper = Integer.parseInt(token.substring(1));

        if(sign == '-')
            return (oper * -1);

        return oper;
    }

    static int findFault(ArrayList<String[]> commands){
        int fault = 0;
        for(int i = 0; i<commands.size();i++){
            String[] instructions = commands.get(i);

            if(instructions[0].equals("jmp") || instructions[0].equals("nop") && Integer.parseInt(instructions[1].substring(1)) != 0){
                ArrayList<String[]> faulted = new ArrayList<String[]>(commands);
                switch (instructions[0]){
                    case "jmp":
                        instructions[0] = "nop";
                        break;
                    case "nop":
                        instructions[0] = "jmp";
                        break;
                }

                faulted.set(i, instructions);
                fault = getAccum(faulted);
                if(fault != -1){
                    return fault;
                }
            }

        }

        return -1;
    }

    static int getAccum(ArrayList<String[]> commands){
        Set<Integer> ran = new HashSet<Integer>();
        int acc = 0;
        int i = 0;
        int idx = 0;

        while(i > -1 && idx < commands.size()){
            idx++;
            String[] instructions = commands.get(i);

            switch (instructions[0]){
                case "nop":
                    i++;
                    break;
                case "acc":
                    acc += oper(instructions[1]);
                    i++;
                    break;
                case "jmp":
                    i += oper(instructions[1]);
                    break;
            }

            if(ran.contains(i))
                return acc;

            ran.add(i);
        }

        return acc;
    }




}
