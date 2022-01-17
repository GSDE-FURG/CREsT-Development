package simulation;

public class comb {
    long c;

    public comb(){
            c = 0;
    }

    public long combination(long n, long p) {
        // if(n > 0 &&  p > 0) {
        long combination = (long) (factorialUsingRecursion(n) / ((factorialUsingRecursion(p) * (factorialUsingRecursion(n - p)))));
        return combination;
        //}
        //return -1;
    }

    public long factorialUsingRecursion(long n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursion(n - 1);
    }

    public static void main(String[] args){
        long cx;
        comb x = new comb();
        long n, p;
        n = 11;
        p = 172;

        p = 2;

        for (int i = 1; i < p; i++) {
            cx = x.combination(172, i);
            System.out.println("C(n,p) = " + "C(" + n + "," + i + ") = " + cx);
        }

    }
}
