import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeInc {
    public static void main(String []argh)
    {
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
        {
            String iP = in.next();
            MyRegex mr = new MyRegex(iP);
            System.out.println(mr.isMatches());
        }
    }


    //Write your code here
    public static class MyRegex {
        private static final Pattern ipPattern = Pattern.compile("^(([\\d]{1,3})\\.){3}[\\d]{1,3}$");
        private String ip;

        public MyRegex(String iP){
            ip = iP;
        }

        public boolean isMatches() {
        Matcher m = ipPattern.matcher(ip);
        if (!m.matches()) {
            return false;
        }

        String[] parts = ip.split("\\.");
        for (String part : parts) {
            int n = Integer.parseInt(part);
            if (n > 255) {
                return false;
            }
        }

        return true;
        }
    }
}
