package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class ASPC {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_aspc.txt"));
        int n = Integer.valueOf(lines.get(0).split("\\s+")[0]);
        int r = Integer.valueOf(lines.get(0).split("\\s+")[1]);

        long sum = 1;
        for(int i=r; i<n; i++){
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
