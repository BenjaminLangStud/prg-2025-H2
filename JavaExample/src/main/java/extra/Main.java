package extra;

public class Main {

    /*
    Visibility-Modifier: public
    Modifier: static
    Return-Type: void
    * Method-Name: main
    * Parameter type: String[]
    Parameter name: blubb
     */
    public static void main(String[] blubb) {
        lastFunWithNumbers();
        // doSomeCalculation( 9, 50);
        // doSomeCalculation(17);
    }
    
    private static void lastFunWithNumbers() {
        double f = 0.1f;
        double sum = 0.0f;
        for (int i = 0; i < 10_000_000; i++) {
            sum += f;
        }
        System.out.println("sum = " + sum);
    }

    private static void moreMoreFunWithNumbers() {
        double f = 1 / 2.0;
        System.out.println("f = " + f);
    }

    private static void moreFunWithNumbers() {
        int a = 5;
        int b = 10;
        int c = ++a * b++;
        System.out.println("c = " + c);
    }

    // 0111'1111 = 127
    // 1000'0000 = -128
    private static void someFunWithNumbers() {
        byte b = 127;
        b++;
        System.out.println(b);
    }

    private static void doSomeCalculation(int a, int b) {
        int sum = a + b;
        System.out.println("Sum: " + sum);
    }

    // Overloaded method
    private static void doSomeCalculation(int a) {
        doSomeCalculation(a, 0);
    }

}
