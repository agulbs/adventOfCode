import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> array = new ArrayList<String>();

        int ans = 0;
        String line;
        String passport = "";
        while((line = reader.readLine()) != null){
            if(line.length() != 0)
                passport += line + " ";
            else{
                if(checkPassport(passport))
                    if(validatePassport(passport))
                        ans++;

                passport = "";
            }
        }

        System.out.println(ans);
    }

    static boolean checkPassport(String passport){
        Set<String> set = new HashSet<String>();

        int base = 0;
        String field = "";
        for(int i=0; i<passport.length(); i++){
            if(passport.charAt(i) == ':'){
                field = passport.substring(base, i);
                set.add(field);
            }

            if(passport.charAt(i) == ' '){
                base = i+1;
            }
        }

        return set.size() == 8 || set.size() == 7 && !set.contains("cid");
    }

    static boolean validatePassport(String passport){
        Set<String> eyeColors = new HashSet<String>();
        eyeColors.add("amb");
        eyeColors.add("blu");
        eyeColors.add("brn");
        eyeColors.add("gry");
        eyeColors.add("grn");
        eyeColors.add("hzl");
        eyeColors.add("oth");

        int base = 0;
        String field = "";
        String value = "";
        boolean flag = true;
        for(int i=0; i<passport.length(); i++){
            if(passport.charAt(i) == ':'){
                field = passport.substring(base, i);
            }

            if(passport.charAt(i) == ' '){
                value = passport.substring(base+4, i);

                switch (field){
                    case "byr":
                        if(Integer.parseInt(value) < 1920 || Integer.parseInt(value) > 2002)
                            return false;
                        break;
                    case "iyr":
                        if(Integer.parseInt(value) < 2010 || Integer.parseInt(value) > 2020)
                            return false;
                        break;
                    case "eyr":
                        if(Integer.parseInt(value) < 2020 || Integer.parseInt(value) > 2030)
                            return false;
                        break;
                    case "hgt":
                        if(value.matches("[0-9]+(cm|in)")){
                            String unit = value.substring(value.length()-2);
                            int height = Integer.parseInt(value.substring(0, value.length()-2));

                            if(unit.equals("in") && (height < 59 || height > 76))
                                return false;
                            if(unit.equals("cm") && (height < 150 || height > 193))
                                return false;
                        }
                        break;
                    case "hcl":
                        if(!value.matches("#[a-f0-9]{6}"))
                            return false;
                        break;
                    case "ecl":
                        if(!eyeColors.contains(value))
                            return false;
                        break;
                    case "pid":
                        if(!value.matches("[0-9]{9}"))
                            return false;
                        break;
                }

                base = i+1;
            }
        }

        return true;
    }
}
