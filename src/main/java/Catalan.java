import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Descriptions 卡特兰数计算
 * @Company
 * @Author Junqson
 * @Date 2019/9/7 13:52
 * @Version 1.0
 */
public class Catalan {

    private static BigInteger[] f = new BigInteger[100];



    public static void calc() {
        f[0] = BigInteger.ONE;

        for (int i = 1; i < 100; i++) {
            BigInteger res = BigInteger.ZERO;
            for (int j = 0; j < i; j++) {
                res = res.add(f[j].multiply(f[i - 1 - j]));
            }
            f[i] = res;
        }
    }

    public static void main(String[] args) {

        calc();

        Arrays.stream(f).forEach(System.out::println);


    }


}
