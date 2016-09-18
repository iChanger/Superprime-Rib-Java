/*
ID: ichangs1
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {
    public static void main (String [] args) throws IOException {
        
        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        int length = Integer.parseInt(f.readLine());
        ArrayList<Integer> primes = new ArrayList<Integer>(Arrays.asList(2,3,5,7));
        ArrayList<Integer> possibles = new ArrayList<Integer>(Arrays.asList(1,3,7,9));
     
        //New Plan: Bootstrap onto the previous primes. We select a number legible, and then we bootstrap on those numbers. Notice that the nubmer of cases decreases as we get higher, which is perfect.
        //The previous powers of 10 that are super primes WILL be the cases for the next power of 10 and so forth
        //Similar to recursion, but I used embedded for loops
        
        label:
        while(Collections.max(primes) < Math.pow(10, length)){
            ArrayList toRemove = new ArrayList();
            ArrayList toAdd = new ArrayList();
            for(int i: primes){
                for(int j: possibles){
                    int bootstrap = Integer.valueOf(Integer.toString(i) + Integer.toString(j));
                    if(bootstrap > Math.pow(10, length)) break label;
                    for(int k = 3; k <= Math.sqrt(bootstrap); k += 2){
                        if((double)bootstrap / (double)k % 1 == 0) break;
                        if(k == (int)Math.sqrt(bootstrap) - 1 || k == (int)Math.sqrt(bootstrap + 1))
                            toAdd.add(bootstrap);
                    }
                }
                toRemove.add(i);
            }
            primes.removeAll(toRemove);
            primes.addAll(toAdd);  
        }
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        
        for(int prime: primes)
            out.println(prime);
         
        out.close();                                  // close the output file
    }
}
