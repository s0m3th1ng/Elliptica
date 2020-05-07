package com.s0meth1ng;

public class Main {

    public static class Pair {
        public int e;
        public Dot R;

        public Pair(Dot R, int e) {
            this.R = R;
            this.e = e;
        }
    }

    public static void main(String[] args) {
        Dot dot = new Dot(0, 5585);
        Dot toAdd = new Dot(0, 5585);
        int count = 1;
        while (!dot.isZero()) {
            System.out.println(dot.toString());
            dot.add(toAdd);
            count++;
        }
        System.out.println(dot.toString());
        System.out.println(count);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        int k = 523, m = 10000, c = 5103;
        Dot D = new Dot(12507, 2027);
        if (D.isOnCurve()) {
            System.out.println("On curve");
        }
        Pair encrypted = encrypt(m, D, toAdd, k);
        int decrypted = decrypt(encrypted.R, encrypted.e, c);
        System.out.println(decrypted);
    }

    public static Pair encrypt(int message, Dot openKey, Dot gen, int k) {
        Dot R = new Dot(0, 0);
        Dot P = new Dot(0, 0);
        for (int i = 0; i < k; i++) {
            R.add(gen);
            P.add(openKey);
        }
        int e = message * P.x % P.getPower();
        return new Pair(R, e);
    }

    public static int decrypt(Dot R, int e, int closedKey) {
        Dot Q = new Dot(0, 0);
        for (int i = 0; i < closedKey; i++) {
            Q.add(R);
        }
        return e * Q.inverse(Q.x) % Q.getPower();
    }
}
