public class Fibonacci {
    /*
        Q1: Messure the time for some big input of n
        Q2: Can you improve the performance of this program ?
     */ 


    private static int callingCounter = 0;
    private static long[] memorizationCache = new long[100];

    public static void main(String[] args) {
        // for (int i = 0; i < 5; i++) {
        //     System.out.println(i +": " + fibonacci(i));
        // }

        int n = 42;
        long startTime = System.nanoTime();
//        int result = fibonacci(n);
        int result = fibonacciIterative(n);
        long endTime = System.nanoTime();
        System.out.println("fibonacci("+ n +") = " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ns");
        System.out.println("Calling Counter: " + callingCounter);
    }

    private static int fibonacciIterative(int n) {
        int lastValue = 1;
        int secondValue = 1;
        while (n > 1) {
            int newValue = lastValue + secondValue;
            lastValue = secondValue;
            secondValue = newValue;
            n--;
        }
        return secondValue;
    }

    private static int fibonacci(int n) {
        callingCounter++;
        if (n <= 1) return 1;

        if (memorizationCache[n] != 0) {
            return (int) memorizationCache[n];
        }
        memorizationCache[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return (int) memorizationCache[n];
    }
}
