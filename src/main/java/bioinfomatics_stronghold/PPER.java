package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PPER {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_pper.txt"));

        String[] s = lines.get(0).split("\\s+");

        int n = Integer.valueOf(s[0]);
        int k = Integer.valueOf(s[1]);

//        int n = 21;
//        int k = 7;

        long res = permutation(n, k);

        System.out.println(n);
        System.out.println(k);
        System.out.println(res);
        System.out.println(res%1000000);


    }

    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static long permutation(long n, long k) {
        long perm = 1;
        for (long i = 0; i < k; i++) {
            perm *= (n - i);
        }
        return perm;
    }


}
