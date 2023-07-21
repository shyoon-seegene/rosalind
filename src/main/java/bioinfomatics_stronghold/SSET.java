package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SSET {

    public static void main(String[] args) throws IOException {

        System.out.println(nCr(3,2));
        System.out.println(nCrB(100,2));


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_sset.txt"));

        int n = Integer.valueOf(lines.get(0));

        long sum = 2;
        for(int i=1; i<n; i++){
            sum += nCrB(n,i);

        }
        System.out.println(sum%1000000);

    }


    private static long nCr(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private static long factorial(int n) {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n - 1)%1000000;
    }

    public static int nCrB(int n, int r){

        BigInteger fn = factorialB(n);
        BigInteger fr = factorialB(r);
        BigInteger fnr = factorialB(n-r);

        BigInteger fd = fr.multiply(fnr);
        BigInteger fres = fn.divide(fd);
        BigInteger res = fres.remainder(new BigInteger("1000000"));

        return res.intValue();
    }

    public static BigInteger factorialB(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
