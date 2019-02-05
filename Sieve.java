import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Sieve {

   private Queue<Integer> primes;
   private static int lastN;

// constructor implicitly created
//    public Sieve() {
//    }

   // implements the sieve algorithm.  Computes all primes up to and including n
   public void computeTo(int n) {
      lastN = n;
      if (n < 2) {
         throw new IllegalArgumentException("Enter a number greater than or equal to 2");
      }
   
      Queue<Integer> consecutiveIntegers = new LinkedList<>();
      for (int i = 2; i <= n; i++) {
         consecutiveIntegers.add(i);
      }
      primes = new LinkedList<>();
      
      int prime;
      do {
         prime = consecutiveIntegers.remove();
         primes.add(prime);
         
         Iterator<Integer> itr = consecutiveIntegers.iterator();
         while (itr.hasNext()) {
            int value = itr.next();
            if (value % prime == 0) {
               itr.remove();
            }
         }
      } while (prime <= Math.sqrt(n));
   
      while (!consecutiveIntegers.isEmpty()) {
         primes.add(consecutiveIntegers.remove());
      }
   }

   // reports primes to console
   public void reportResults() {
      int count = 0;
      for (int value : primes) {
         count++;
         System.out.print(value + " ");
         if (count % 12 == 0) {
            System.out.println();
         }
      }
      System.out.println();
   }

   // returns value of n that was used the last time computeTo was called
   public int getMax() {
      if (lastN < 2) {
         throw new IllegalStateException();  // This could also be done using a boolean class variable initialized to false and set to true after a legal call to computeTo()
      }
      return lastN;   
   }

   // returns the number of primes that were found on the last call to computeTo
   public int getCount() {
      if (lastN < 2) {
         throw new IllegalStateException();  // This could also be done using a boolean class variable initialized to false and set to true after a legal call to computeTo()
      }
      return primes.size();
   }
}