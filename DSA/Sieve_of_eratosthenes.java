import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Sieve_of_eratosthenes {
        private static void solve(){
            // Precomputes all prime and non-prime numbers
            int max = 1000000;
            boolean[] isprime = new boolean[max+1];
            //initialize all as prime
            Arrays.fill(isprime, true);
            isprime[1]=false;

            for(int i=2; i*i<=max; i++){
                if(isprime[i] == true){
                    //make the multiples non-prime
                    for(int j=i*i; j<=max; j+=i){
                        isprime[j] = false;
                    }
                }
            }
        }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // String s = sc.next();
        solve();
        sc.close();
    }
}
