import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class Multiply<T> extends RecursiveTask<BigInteger> {

    private final BigInteger left;
    private final BigInteger right;
    private static final BigInteger THRESHOLD = new BigInteger("2");

    public Multiply(BigInteger left, BigInteger right) {
        this.left = left;
        this.right = right;
    }

    public BigInteger shiftDigit(BigInteger in, int length) {
        String str = String.format("%0" + length + "d", in);
        return new BigInteger(str.substring(0, length / 2));
    }

    public BigInteger lastDigit(BigInteger in, int length) {
        String str = String.format("%0" + length + "d", in);
        return new BigInteger(str.substring(length / 2));
    }

    public BigInteger padDigits(BigInteger in, int length) {
        String str = "0".repeat(length) + in.toString();
        return new BigInteger(str);
    }

    @Override
    public BigInteger compute() {

        int length = Math.max(this.left.toString().length(), 
            this.right.toString().length());
        if (left.compareTo(THRESHOLD) < 0) {
            return left.multiply(right);
        }
        BigInteger a = shiftDigit(left, length);
        BigInteger b = shiftDigit(right, length);
        BigInteger c = lastDigit(left, length);
        BigInteger d = lastDigit(right, length);

        Multiply<BigInteger> step3 = new Multiply<>(a, c);
        Multiply<BigInteger> step4 = new Multiply<>(b, d);
        Multiply<BigInteger> step5 = new Multiply<>(a.add(b), c.add(d));
        step3.fork();
        step4.fork();
        step5.fork();
        BigInteger abcd= step5.join();
        BigInteger bd = step4.join();
        BigInteger ac = step3.join();
        BigInteger step6 = abcd.subtract(ac.add(bd));

        return padDigits(ac, length).add(bd).add(padDigits(step6, length / 2));
    }
}
