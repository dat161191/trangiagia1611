package aoca.p2;

import aoca.p1.Acc;

public class Test extends Acc {
    public static void main(String[] args) {
        Acc obj = new Test();
        int n1 = obj.s;
//        int n2 = ((Test) obj).r;
        int n2 = ((Test) obj).r;
        Test obj2 = new Test();
    }
}