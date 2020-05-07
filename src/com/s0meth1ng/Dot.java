package com.s0meth1ng;

public class Dot {
    public int x;
    public int y;

    private int gf = 31991;
    private int a = 31988;
    private int b = 1000;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPower() {
        return this.gf;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void add(Dot toAdd) {
        if (!((toAdd.x == 0)&&(toAdd.y == 0))) {
            if ((this.x == 0) && (this.y == 0)) {
                this.x = toAdd.x;
                this.y = toAdd.y;
            } else if ((this.x == toAdd.x) && (this.y == gf - toAdd.y)) {
                this.x = 0;
                this.y = 0;
            } else {
                int m, v;
                if ((this.x != toAdd.x) || (this.y != toAdd.y)) {
                    m = toField(toField(toAdd.y - this.y) * inverse(toField(toAdd.x - this.x)));
                    v = toField(toField(toField(this.y*toAdd.x) - toField(toAdd.y*this.x)) * inverse(toField(toAdd.x - this.x)));
                } else {
                    m = toField(toField(toField(3*this.x*this.x) + this.a) * inverse(toField(2*this.y)));
                    v = toField((toField(toField(toField(-this.x)*this.x)*this.x) + toField(this.a*this.x) + toField(2*this.b)) * inverse(toField(2*this.y)));
                }
                int resX = toField(m*m - this.x - toAdd.x);
                int resY = toField(-m*resX - v);
                this.x = resX;
                this.y = resY;
            }
        }
    }

    public boolean isZero() {
        return ((this.x == 0) && (this.y == 0));
    }

    public boolean isOnCurve() {
        return this.isZero() || (toField(toField(toField(this.x * this.x) * this.x) + toField(this.a * this.x) + this.b) == toField((int) Math.pow(this.y, 2)));
    }

    public int inverse(int elem) {
        if (elem == 0) {
            return 0;
        }
        int inverted = 2;
        while (inverted * elem % gf != 1) {
            inverted++;
        }
        return inverted;
    }

    private int toField(int a) {
        if (a >= gf) {
            return a % gf;
        }
        while (a < 0) {
            a += gf;
        }
        return a;
    }
}
