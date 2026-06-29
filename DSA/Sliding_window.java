import java.io.*;
import java.util.*;

public class Sliding_window {
    // Longest substring without duplicate character
    private static int solve(String s){
        int n = s.length();
        if(n==0) return 0;

        // Initialize 'l'
        int l = 0, maxLen = Integer.MIN_VALUE;
        Set<Character> set = new HashSet<>();

        // Expanding the window
        for(int r = 0; r < n; r++){

            //Shrink the window - until condition not satisfied
            while(set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                l++;
            }

            // Operation to be done when Condition satisfied
            set.add(s.charAt(r));
            maxLen = Math.max(maxLen, r-l+1);
        }
        return maxLen;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        solve(s);
        sc.close();
    }
}